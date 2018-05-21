package com.passvault.abhi.passwordvault.background;

import java.util.Random;

public class Passgen {

        private final  String specialchar ="!@$%&";
        private final String number ="0123456789";
        private final  String LOWER = "abcdefghijklmnopqrstuvwxyz";
        private final  String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        private int length;
        private String especialchar, enumber, eLOWER, eUPPER;

        public Passgen(int len, String exclusions) {

            length = len;
            // Replace all the excluded characters from all four strings
            especialchar = especialchar.replaceAll("["+exclusions+"]","");
            enumber = enumber.replaceAll("["+exclusions+"]","");
            eLOWER = eLOWER.replaceAll("["+exclusions+"]","");
            eUPPER = eUPPER.replaceAll("["+exclusions+"]","");
        }
        // Randomly generates one number from 0 to 3 to select one of the four strings
        // And then generates one number randomly to select one of the character from the string
        private String gen() {
            String pass="";
            Random rand = new Random();
            int templen = length;
            while (templen>0) {
                int  k = rand.nextInt(4);
                if(k==0) {
                    int  n = rand.nextInt(especialchar.length());
                    String q = ""+especialchar.charAt(n);
                    if(!pass.contains(q)) {
                        pass+=especialchar.charAt(n);
                        templen--;
                    }else {
                        continue;
                    }
                }else if (k==1) {
                    int  n = rand.nextInt(enumber.length());
                    String q = ""+enumber.charAt(n);
                    if(!pass.contains(q)) {
                        pass+=enumber.charAt(n);
                        templen--;
                    }else {
                        continue;
                    }
                }else if (k==2) {
                    int  n = rand.nextInt(eLOWER.length());
                    String q = ""+eLOWER.charAt(n);
                    if(!pass.contains(q)) {
                        pass+=eLOWER.charAt(n);
                        templen--;
                    }else {
                        continue;
                    }
                }else {
                    int  n = rand.nextInt(eUPPER.length());
                    String q = ""+eUPPER.charAt(n);
                    if(!pass.contains(q)) {
                        pass+=eUPPER.charAt(n);
                        templen--;
                    }else {
                        continue;
                    }
                }
            }
            return pass;
        }
        public String generate(){
            String result = gen();
            return result;
        }

}
