package com.javarush.demo;

public class ExpressionTreeTask {
    public static void main(String[] args) {
        // (2 + 3) * 4
        Expr expr = new Mul(new Add(new Num(2), new Num(3)), new Num(4));
        System.out.println(expr.show() + " = " + expr.eval());

        // 10 + 2 * 7
        Expr second = new Add(new Num(10), new Mul(new Num(2), new Num(7)));
        System.out.println(second.show() + " = " + second.eval());
    }
}

abstract class Expr {
    abstract int eval();

    abstract String show();
}

class Num extends Expr {
    private int value;

    Num(int value) {
        this.value = value;
    }

    @Override
    int eval() {
        return value;
    }

    @Override
    String show() {
        return String.valueOf(value);
    }
}

class Add extends Expr {
    private Expr left;
    private Expr right;

    Add(Expr left, Expr right) {
        this.left = left;
        this.right = right;
    }

    @Override
    int eval() {
        return left.eval() + right.eval();
    }

    @Override
    String show() {
        return "(" + left.show() + " + " + right.show() + ")";
    }
}

class Mul extends Expr {
    private Expr left;
    private Expr right;

    Mul(Expr left, Expr right) {
        this.left = left;
        this.right = right;
    }

    @Override
    int eval() {
        return left.eval() * right.eval();
    }

    @Override
    String show() {
        return "(" + left.show() + " * " + right.show() + ")";
    }
}
