package com.passvault.abhi.passwordvault.background;

import java.util.Random;

public class Passgen {

        private   String specialchar ="!@$%&";
        private   String number ="0123456789";
        private   String LOWER = "abcdefghijklmnopqrstuvwxyz";
        private   String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        private int length;

        public Passgen(int len) {
            length = len;
        }

        private String gen() {
            String pass="";
            Random rand = new Random();
            int templen = length;
            while (templen>0) {
                int  k = rand.nextInt(4);
                if(k==0) {
                    int  n = rand.nextInt(specialchar.length());
                    String q = ""+specialchar.charAt(n);
                    if(!pass.contains(q)) {
                        pass+=specialchar.charAt(n);
                        templen--;
                    }else {
                        continue;
                    }
                }else if (k==1) {
                    int  n = rand.nextInt(number.length());
                    String q = ""+number.charAt(n);
                    if(!pass.contains(q)) {
                        pass+=number.charAt(n);
                        templen--;
                    }else {
                        continue;
                    }
                }else if (k==2) {
                    int  n = rand.nextInt(LOWER.length());
                    String q = ""+LOWER.charAt(n);
                    if(!pass.contains(q)) {
                        pass+=LOWER.charAt(n);
                        templen--;
                    }else {
                        continue;
                    }
                }else {
                    int  n = rand.nextInt(UPPER.length());
                    String q = ""+UPPER.charAt(n);
                    if(!pass.contains(q)) {
                        pass+=UPPER.charAt(n);
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
