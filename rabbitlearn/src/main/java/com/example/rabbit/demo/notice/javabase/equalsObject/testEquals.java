package com.example.rabbit.demo.notice.javabase.equalsObject;

public class testEquals {
    public static void main(String[] args) {



        B b = new B();
        Double num=-42D;
        Long sco=-68L;
        b.setBalance(num);
        b.setScore(sco);

        B c = new B();
        c.setBalance(-num);
        c.setScore(-sco);
        System.out.println("bnum++++"+b.getBalance());
        System.out.println("bsco++++"+b.getScore());
        System.out.println("cnum++++"+c.getBalance());
        System.out.println("csco++++"+c.getScore());

    }
}
