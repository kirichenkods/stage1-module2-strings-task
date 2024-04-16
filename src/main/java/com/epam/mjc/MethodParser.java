package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     *      1. access modifier - optional, followed by space: ' '
     *      2. return type - followed by space: ' '
     *      3. method name
     *      4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     *      accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     *      private void log(String value)
     *      Vector3 distort(int x, int y, int z, float magnitude)
     *      public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {

        String argsString = signatureString.substring(signatureString.indexOf('(') + 1,
                signatureString.indexOf(')'));
        List<String> args = List.of(argsString.split(", "));

        List<String> signature = List.of(signatureString
                .substring(0, signatureString.indexOf('('))
                .split(" "));

        List<MethodSignature.Argument> arguments = getArguments(args);
        MethodSignature methodSignature = getMethodSignature(signature, arguments);

        if (signature.size() == 3) {
            methodSignature.setAccessModifier(signature.get(0));
            methodSignature.setReturnType(signature.get(1));
        } else {
            methodSignature.setReturnType(signature.get(0));
        }

        return methodSignature;
    }

    private MethodSignature getMethodSignature(List<String> signature,
                                               List<MethodSignature.Argument> arguments) {
        MethodSignature methodSignature;
        if (arguments.size() > 0) {
            methodSignature = new MethodSignature(
                    signature.get(signature.size() - 1),
                    arguments);
        } else {
            methodSignature = new MethodSignature(signature.get(signature.size() - 1));
        }
        return methodSignature;
    }

    private List<MethodSignature.Argument> getArguments(List<String> args) {
        List<MethodSignature.Argument> arguments = new ArrayList<>();
        if (!args.get(0).isEmpty()) {
            for (String arg : args) {
                List<String> argList = List.of(arg.split(" "));
                MethodSignature.Argument argument = new MethodSignature.Argument(
                        argList.get(0), argList.get(1));
                arguments.add(argument);
            }
        }
        return arguments;
    }
}
