package com.genesiscode.quotation.utils;

public final class Preconditions {

    /**
     * This class must not be instantiable anywhere.
     */
    private Preconditions() {}

    /**
     * It verifies if the expression meets the condition. If it does not, then it throws an exception.
     *
     * @param expression the required expression evaluated in the client.
     * @param errorMessage the error message to set if it does not meet the condition.
     * @throws IllegalArgumentException when it does not meet the condition.
     */
    public static void checkArgument(boolean expression, String errorMessage) {
        if(! expression)
            throw new IllegalStateException(errorMessage);
    }

    /**
     *
     *
     * */
    public static void checkDataType(Object data, Class<?> classVerify, String message) {
        if(! data.getClass().equals(classVerify))
            throw new IllegalStateException(message);
    }

}
