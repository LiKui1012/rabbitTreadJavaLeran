//package com.example.rabbit.demo.notice;
//
//public class ewm {
//    public static void main(String[] args) throws Exception {
//        String text = "http://www.dans88.com.cn";
//        QRCodeUtil.encode(text, "d:/MyWorkDoc/my180.jpg", "d:/MyWorkDoc", true);
//    }
//    public static String decode(String path) throws Exception {
//        return QRCodeUtil.decode(new File(path));
//    }
//    public static String decode(File file) throws Exception {
//        BufferedImage image;
//        image = ImageIO.read(file);
//        if (image == null) {
//            return null;
//        }
//        BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(
//                image);
//        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
//        Result result;
//        Hashtable hints = new Hashtable();
//        hints.put(DecodeHintType.CHARACTER_SET, CHARSET);
//        result = new MultiFormatReader().decode(bitmap, hints);
//        String resultStr = result.getText();
//        return resultStr;
//    }
//    public static void encode(String content, OutputStream output)
//            throws Exception {
//        QRCodeUtil.encode(content, null, output, false);
//    }
//}
