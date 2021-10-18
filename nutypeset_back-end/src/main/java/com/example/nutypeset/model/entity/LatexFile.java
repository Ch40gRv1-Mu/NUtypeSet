package com.example.nutypeset.model.entity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.Map;

public class LatexFile {
    private String paperType="a4paper";
    private String fileType="article";
    private String encodeType = "utf8";
    private String title ="none";
    private String author = "none";
    private String additionalHeader = "";
    private String date = "\\today";
    private String additionalPackets="";
    private String codeMiddle="\\begin{center}\nWelcome to nutypeset\n\\end{center}\n";

    private LinkedList<String> packets;

    {
        packets = new LinkedList();
    }

    private Map<String,String> setlength;
    private Map<String,String> addtolength;
    private LinkedList<Window> windows;



    @Autowired
    public LatexFile(){
        try {
            //BufferedReader reader = new BufferedReader(new FileReader("/latexNecessaryPackets.txt"));
            LatexNecessaryPackets necessaryPackets= new LatexNecessaryPackets();
            LinkedList<String> packetsList = necessaryPackets.getPackets();
            for (int i=0; i<packetsList.size(); i++){
                String line = packetsList.get(i);
                String [] tmpLis = line.split("%");
                tmpLis = tmpLis[0].split(";");
                if (tmpLis.length == 1){
                    this.packets.add("\\usepackage{"+tmpLis[0]+"}\n");
                }
                else if (tmpLis.length == 2){
                    this.packets.add("\\usepackage["+tmpLis[1]+"]{"+tmpLis[0]+"}\n");
                }

            }



        }catch (Exception e) {
            e.printStackTrace();
        }









    }

    /*
    //页面设置
    //https://blog.csdn.net/yanhuatangtang/article/details/53905828
    private String collumnsep="10.0pt"; //列间距
    private String topmargin = "17.0pt"; //页眉到页边的距离
    private String topskip="10.0pt"; //页眉与正文的距离
    private String textheight = "740.26378pt"; //正文的高度
    private String textwidth="473.0374pt";//文本的宽度
    private String oddsidemargin = "53.0pt"; //奇数页的左面页边距
    private String evensideMargin = "54.0pt"; //偶数页的左面页边距

    //段落
    private String parindent="15.0p"; //段落缩进距离
    private String parskip = "0.0pt plus 1.0pt"; //段落间的距离

    //浮动图表
    private String floatsep="12.0pt plus 2.0pt minus 2.0pt"; //浮动对象之间的距离
    private String textfloatsep = "20.0pt plus 2.0pt minus 4.0pt"; //最后一个浮动对象顶端或第一个浮动对象底端与正文之间的距离
    private String dbltextfloatsep="20.0pt plus 2.0pt minus 4.0pt";//是在双列输出时用 \textfloatsep 的数值
    private String abovecaptionskip = "10.0p"; //标题上方的距离
    private String belowcaptionskip = "0.0pt"; //标题下方的距离
    private String intextsep = "12.0pt plus 2.0pt minus 2.0pt";//文中浮动顶端与底端所留的距离
    private String dblfloatsep = "12.0pt plus 2.0pt minus 2.0pt" ;//是在双列输出时用 \floatsep 的数值

    //数学公式
    private String abovedisplayskip; //公式前的距离
    private String belowdisplayskip;// 公式后面的距离
    private String arraycolsep; // 在一个array中列之间的空白长度

     */

    public String getPaperType() {
        return paperType;
    }

    public void setPaperType(String paperType) {
        this.paperType = paperType;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }


    public void addPackets(String packet)
    {
        this.packets.add(packet);
    }

    public Map<String, String> getSetlength() {
        return setlength;
    }

    public void setSetlength(Map<String, String> setlength) {
        this.setlength = setlength;
    }

    public void addSetlength(@org.jetbrains.annotations.NotNull Map<String,String> addParam){
        for (Map.Entry<String, String> entry : addParam.entrySet()) {
            this.setlength.put(entry.getKey(),entry.getValue());
        }
    }

    public Map<String, String> getAddtolength() {
        return addtolength;
    }

    public void setAddtolength(Map<String, String> addtolength) {
        this.addtolength = addtolength;
    }

    public String getEncodeType() {
        return encodeType;
    }

    public void setEncodeType(String encodeType) {
        this.encodeType = encodeType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPackets(LinkedList<String> packets) {
        this.packets = packets;
    }



    public String getAdditionalHeader() {
        return additionalHeader;
    }

    public void setAdditionalHeader(String additionalHeader) {
        this.additionalHeader = additionalHeader;
    }

    public LinkedList<Window> getWindows() {
        return windows;
    }

    public void setWindows(LinkedList<Window> windows) {
        this.windows = windows;
    }

    public void addWindows(Window windows) {
        this.windows.add(windows);
    }

    public String getAdditionalPackets() {
        return additionalPackets;
    }

    public void setAdditionalPackets(String additionalPackets) {
        this.additionalPackets = additionalPackets;
    }

    public String getCodeMiddle() {
        return codeMiddle;
    }

    public void setCodeMiddle(String codeMiddle) {
        this.codeMiddle = codeMiddle;
    }

    public String returnLatexFile(){
        String latexFile = "";

        latexFile += ("\\documentclass[" +this.paperType+"]{" +this.fileType+"}\n");
        latexFile += "\\usepackage["+this.encodeType+"]{inputenc}\n";
        /*

        for (Map.Entry<String, String> entry : setlength.entrySet()) {
            latexFile += ("\\setlength{\\"+entry.getKey()+"}{"+entry.getValue()+"}\n");
            //System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }

        for (Map.Entry<String, String> entry : addtolength.entrySet()) {
            latexFile += ("\\addtolength{\\"+entry.getKey()+"}{"+entry.getValue()+"}\n");
            //System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }

         */

        //add package
        for (String pkt: packets){
            latexFile += pkt;
        }
        //latexFile += this.additionalPackets;
        //latexFile += additionalHeader;

        //add title
        if ( this.title != "none")  latexFile+=("\\title{"+this.title+"}\n");

        //add author
        if (this.author != "none") latexFile+=("\\author{"+this.author+"}\n");

        //add date
        if (this.date != "none")  latexFile+=("\\date{"+this.author+"}\n");


        //start of document
        latexFile += ("\\begin{document}\n");
        if ( this.title != "none")  latexFile += "\\maketitle\n";
        /*
        for (Window window: windows){
            latexFile+= window.getCode(); //把每个window拼在一起

        }*/
        latexFile+=this.codeMiddle;



        //end of document
        latexFile += ("\\end{document}\n");


        return latexFile;

    }
}
