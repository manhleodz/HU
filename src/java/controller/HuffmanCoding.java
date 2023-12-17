package controller;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanCoding {

    private Map<Character, String> codes;
    private int index;

    private class Node {

        int frequency;
        char data;
        Node left, right;

        Node(char data, int frequency) {
            this.data = data;
            this.frequency = frequency;
            left = right = null;
        }
    }

    private class MyComparator implements Comparator<Node> {

        public int compare(Node x, Node y) {
            return x.frequency - y.frequency;
        }
    }

    public HuffmanCoding() {
        codes = new HashMap<>();
        index = 0;
    }

    private void getCodes(Node node, String currCode) {
        if (node.left == null && node.right == null) {
            codes.put(node.data, currCode);
            return;
        }

        getCodes(node.left, currCode + "0");
        getCodes(node.right, currCode + "1");
    }

    private String makeString(Node node) {
        if (node.left == null && node.right == null) {
            return "'" + node.data;
        }
        return "0" + makeString(node.left) + "1" + makeString(node.right);
    }

    private Node makeTree(String treeString) {
        Node node = new Node('\0', 0);
        if (treeString.charAt(index) == '\'') {
            index++;
            node = new Node(treeString.charAt(index), 0);
            index++;
            return node;
        }
        index++;
        node.left = makeTree(treeString);
        index++;
        node.right = makeTree(treeString);
        return node;
    }

    public String[] encode(String data) {

        Map<Character, Integer> mp = new HashMap<>();
        for (int i = 0; i < data.length(); i++) {
            char ch = data.charAt(i);
            mp.put(ch, mp.getOrDefault(ch, 0) + 1);
        }

        if (mp.size() == 0) {
            String finalString = "zer#";
            String outputMessage = "Compression complete and file sent for download\nCompression Ratio : "
                    + (double) (data.length() / finalString.length());
            return new String[]{finalString, outputMessage};
        }

        if (mp.size() == 1) {
            char key = '\0';
            int value = 0;
            for (Map.Entry<Character, Integer> entry : mp.entrySet()) {
                key = entry.getKey();
                value = entry.getValue();
            }
            String finalString = "one#" + key + "#" + value;
            String outputMessage = "Compression complete and file sent for download\nCompression Ratio : "
                    + (double) (data.length() / finalString.length());
            return new String[]{finalString, outputMessage};
        }

        PriorityQueue<Node> heap = new PriorityQueue<>(new MyComparator());

        for (Map.Entry<Character, Integer> entry : mp.entrySet()) {
            heap.add(new Node(entry.getKey(), entry.getValue()));
        }

        while (heap.size() >= 2) {
            Node minNode1 = heap.poll();
            Node minNode2 = heap.poll();
            Node mergedNode = new Node('\0', minNode1.frequency + minNode2.frequency);
            mergedNode.left = minNode1;
            mergedNode.right = minNode2;
            heap.add(mergedNode);
        }

        Node huffmanTree = heap.poll();
        codes = new HashMap<>();
        getCodes(huffmanTree, "");

        StringBuilder binaryString = new StringBuilder();
        for (int i = 0; i < data.length(); i++) {
            binaryString.append(codes.get(data.charAt(i)));
        }

        int paddingLength = (8 - (binaryString.length() % 8)) % 8;
        for (int i = 0; i < paddingLength; i++) {
            binaryString.append('0');
        }

        StringBuilder encodedData = new StringBuilder();
        for (int i = 0; i < binaryString.length();) {
            int currNum = 0;
            for (int j = 0; j < 8; j++, i++) {
                currNum *= 2;
                currNum += binaryString.charAt(i) - '0';
            }
            encodedData.append((char) currNum);
        }

        String treeString = makeString(huffmanTree);
        int tsLength = treeString.length();
        String finalString = tsLength + "#" + paddingLength + "#" + treeString + encodedData;
        String outputMessage = "Compression complete and file sent for download\nCompression Ratio : "
                + (double) (data.length() / finalString.length());
        return new String[]{finalString, outputMessage};
    }

    public String[] decode(String data) {
        int k = 0;
        StringBuilder temp = new StringBuilder();
        while (data.charAt(k) != '#') {
            temp.append(data.charAt(k));
            k++;
        }
        System.out.println(temp);
        if (temp.toString().equals("zer")) {
            String decodedData = "";
            String outputMessage = "Decompression complete and file sent for download";
            return new String[]{decodedData, outputMessage};
        }
        if (temp.toString().equals("one")) {
            data = data.substring(k + 1);
            k = 0;
            temp = new StringBuilder();
            while (data.charAt(k) != '#') {
                temp.append(data.charAt(k));
                k++;
            }
            char oneChar = temp.charAt(0);
            data = data.substring(k + 1);
            int strLen = Integer.parseInt(data);
            StringBuilder decodedData = new StringBuilder();
            for (int i = 0; i < strLen; i++) {
                decodedData.append(oneChar);
            }
            String outputMessage = "Decompression complete and file sent for download";
            return new String[]{decodedData.toString(), outputMessage};
        }

        data = data.substring(k + 1);
        int tsLength = Integer.parseInt(temp.toString());
        k = 0;
        temp = new StringBuilder();
        while (data.charAt(k) != '#') {
            temp.append(data.charAt(k));
            k++;
        }
        data = data.substring(k + 1);
        int paddingLength = Integer.parseInt(temp.toString());
        temp = new StringBuilder();
        for (k = 0; k < tsLength; k++) {
            temp.append(data.charAt(k));
        }
        data = data.substring(k);
        String treeString = temp.toString();
        temp = new StringBuilder();
        for (k = 0; k < data.length(); k++) {
            temp.append(data.charAt(k));
        }
        String encodedData = temp.toString();
        index = 0;
        Node huffmanTree = makeTree(treeString);

        StringBuilder binaryString = new StringBuilder();
        for (int i = 0; i < encodedData.length(); i++) {
            int currNum = encodedData.charAt(i);
            StringBuilder currBinary = new StringBuilder();
            for (int j = 7; j >= 0; j--) {
                int foo = currNum >> j;
                currBinary.append(foo & 1);
            }
            binaryString.append(currBinary);
        }

        binaryString = new StringBuilder(binaryString.substring(0, binaryString.length() - paddingLength));

        StringBuilder decodedData = new StringBuilder();
        Node node = huffmanTree;
        for (int i = 0; i < binaryString.length(); i++) {
            if (binaryString.charAt(i) == '1') {
                node = node.right;
            } else {
                node = node.left;
            }

            if (node.left == null && node.right == null) {
                decodedData.append(node.data);
                node = huffmanTree;
            }
        }
        String outputMessage = "Decompression complete and file sent for download";
        return new String[]{decodedData.toString(), outputMessage};
    }
}
