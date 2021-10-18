package com.example.nutypeset.model.entity;

import java.util.LinkedList;

public class LatexNecessaryPackets {
    private LinkedList<String> packets= new  LinkedList<String>(){{
        add("amsmath;%数学公式");
        add("graphicx;%插图");
        add("xcolor;%颜色");
        add("ctex;%中文");
        add("listings;%code");
        add("float;%fixed position");
        add(" parskip;%LaTeX 用/parskip表示段距，我一般把它设为1ex");
        add(" amsmath,amssymb;%数学公式");
        add("inputenc;utf8%编码方式");
        add(" babel;english%具有多语种处理功能的宏包") ;
        }
    };

    public LinkedList<String> getPackets() {
        return packets;
    }

}
