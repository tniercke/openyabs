/*
 *  This file is part of MP.
 *
 *      MP is free software: you can redistribute it and/or modify
 *      it under the terms of the GNU General Public License as published by
 *      the Free Software Foundation, either version 3 of the License, or
 *      (at your option) any later version.
 *
 *      MP is distributed in the hope that it will be useful,
 *      but WITHOUT ANY WARRANTY; without even the implied warranty of
 *      MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *      GNU General Public License for more details.
 *
 *      You should have received a copy of the GNU General Public License
 *      along with MP.  If not, see <http://www.gnu.org/licenses/>.
 */
package mpv5;

import mpv5.server.MPServer;

/**
 *
 *  anti
 */
public class Test {

    public static void main(String[] args) {

        new MPServer();

//        try {
//        try {
//            new PDFFormTest(new File("/home/anti/Desktop/euerformular.pdf")).fillFields();
////        new SplashScreen(new ImageIcon(Test.class.getResource("/mpv5/resources/images/background.png")));
////        try {
////        try {
////            new XMLReader().newDoc(new File("contacts.xml"), true);
////
////        } catch (JDOMException ex) {
////            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
////        } catch (IOException ex) {
////            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
////        }
////            XMLReader r = new XMLReader();
////            r.newDoc(new File("contacts.xml"), true);
////            ArrayList<DatabaseObject> l = r.getObjects(new Contact());
////
////            for (int i = 0; i < l.size(); i++) {
////
////                DatabaseObject databaseObject = l.get(i);
////                System.out.println(databaseObject.__getCName());
////
////            }
////        } catch (Exception ex) {
////           ex.printStackTrace();
////        }
//        } catch (IOException ex) {
//            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (DocumentException ex) {
//            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        new SplashScreen(new ImageIcon(Test.class.getResource("/mpv5/resources/images/background.png")));
//        try {
//        try {
//            new XMLReader().newDoc(new File("contacts.xml"), true);
//
//        } catch (JDOMException ex) {
//            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
//        }
//            XMLReader r = new XMLReader();
//            r.newDoc(new File("contacts.xml"), true);
//            ArrayList<DatabaseObject> l = r.getObjects(new Contact());
//
//            for (int i = 0; i < l.size(); i++) {
//
//                DatabaseObject databaseObject = l.get(i);
//                System.out.println(databaseObject.__getCName());
//
//            }
//        } catch (Exception ex) {
//           ex.printStackTrace();
//        }
//        String[][] ihreDaten = new String[][]{{"3", "Item 3"},{"4", "Item 4"},{"1", "Item 1"},{"2", "Item 2"}};
//
//        ComboBoxModel model = MPComboBoxModelItem.toModel(ihreDaten);
//        JComboBox combobox = new JComboBox(model);
//
//        combobox.setSelectedIndex(MPComboBoxModelItem.getItemID("3", combobox.getModel()));
//
//        String value = ((MPComboBoxModelItem)combobox.getSelectedItem()).getValue();//"Item 3"
//        String id = ((MPComboBoxModelItem)combobox.getSelectedItem()).getId();//"3"
//
//        int indexDesItems3 = MPComboBoxModelItem.getItemID("3", combobox.getModel());// 0
//        int indexDesItems1 = MPComboBoxModelItem.getItemIDfromValue("Item 1", combobox.getModel());// 2
//
//        DateConverter.getQuarter();Locale.getDefault();
//            new DecimalFormat("'#'#'#'''-000").format(1000l);
//
//
//            File file = new File("D:\\projeler\\hal\\monitor\\hal_monitor\\msg_deneme.xml");
//            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//            DocumentBuilder db = dbf.newDocumentBuilder();
//            Document doc = db.parse(file);
//            doc.getDocumentElement().normalize();
//            System.out.println("Root element " + doc.getDocumentElement().getNodeName());
//
//            NodeList nodeLst = doc.getElementsByTagName("messages");
//
//
//            for (int s = 0; s < nodeLst.getLength(); s++) {
//                Node fstNode = nodeLst.item(s);
//                if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
//
//                    Element fstElmnt = (Element) fstNode;
//                    NodeList msgNameElmntLst = fstElmnt.getElementsByTagName("name");
////                    Element msgNameElmnt = (Element) msgNameElmntLst.item(0);
//                    NodeList msgName = msgNameElmnt.getChildNodes();
//                    System.out.println("Message Name : " + (msgName.item(0)).getNodeValue());
//                    NodeList trMode = fstElmnt.getElementsByTagName("trMode");
//                    Element trModeElmnt = (Element) trMode.item(0);
//                    NodeList tr = trModeElmnt.getChildNodes();
//                    System.out.println("TRMode : " + (tr.item(0)).getNodeValue());
//                    /***GET THE NAME OF FIELD NODE**/
//
//                     NodeList nodeLst_fields = fstNode.getChildNodes();
//
//                    for (int i = 0; i < nodeLst_fields.getLength(); i++) {
//                       Node fstFieldNode = nodeLst_fields.item(i);
//
//                            if (fstFieldNode.getNodeType() == Node.ELEMENT_NODE && fstFieldNode.getNodeName().equals("fields")) {
//                                Element fstFieldElmnt = (Element) fstFieldNode;
//                                NodeList fields = fstFieldElmnt.getElementsByTagName("name");
//                                Element fieldNameElmnt = (Element) fields.item(0);
//                                NodeList field = fieldNameElmnt.getChildNodes();
//                                System.out.println("Field Name : " + (field.item(0)).getNodeValue());
//                                //} //end field if
//                            } //end field for
//
//
//                    } // end if
//                } // end forChuck Norris doesn’t deploy web applications, he roundhouse kicks them into the server.
//            } // end tryA synchronize doesn’t protect against Chuck Norris, if he wants the object, he takes it.
//        } catch (Exception ex) {
//            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
//        }

    }
}
