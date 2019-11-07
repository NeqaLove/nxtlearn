package it.nextre.academy.nxtlearn.myutils;

//RuntimeException = UNchecked (non devono essere per forza gestite)
//Exception o ogni sua altra figlia tranne RuntimeException = CHECKED (Devono essere gestite obbligatoriamente)

public class IllegalParamsException extends RuntimeException {

    public IllegalParamsException(String msg) {
        super(msg);
    }

    public IllegalParamsException() {
        super("parametro non valido");
    }


}//end class
