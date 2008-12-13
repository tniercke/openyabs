/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mpv5.globals;

import mpv5.utils.files.FileReaderWriter;

/**
 *
 * @author Andreas
 */
public class Messages {

    public static final String START_MESSAGE =
        "\n"+
        "\n  MP " + Constants.VERSION + " Copyright (C) 2006-2008 Andreas Weber\n"+
        "\n  This program comes with ABSOLUTELY NO WARRANTY."+
        "\n  MP is free software, and you are welcome to redistribute it " +
        "\n  under certain conditions;" +
        "\n  Start with -license for details.\n" +
        "\n  Start with -help for command line options.\n" +
        "*****************************************************************\n\n";

//    public static final String GPL = new FileReaderWriter("license.txt").read();


    public static String NEW_CONTACT = "New Contact";
    public static String NEW_CUSTOMER = "New Customer";
    public static String NEW_SUPPLIER = "New Supplier";
    public static String NEW_MANUFACTURER = "New Manufacturer";
    public static String NEW_OFFER = "New Offer";
    public static String NEW_BILL = "New Bill";

}