/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.core.utils;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;

import java.io.*;
import java.util.List;
import java.util.Properties;

/**
 * <p>
 * 有序的properties的工具类，由于JDK自身的写会乱序，所以采用这个工具类来保证读写次序正常.
 * </p>
 *
 * @author Nick Lv
 * @version 1.0 2013-01-02 12:57 PM
 * @created 2017 /03/01 10:44:10
 * @since JDK 1.5
 */
public class OrderProperties extends Properties implements Externalizable {
    /**
     * 序列化ID
     */
    private static final long serialVersionUID = -762117852594617585L;
    /**
     * The constant keyValueSeparators.
     */
    private static final String KEYVALUESEARATORS = "=: \t\r\n\f";
    /**
     * The constant strictKeyValueSeparators.
     */
    private static final String STRICTKEYVALUESEARATORS = "=:";
    /**
     * The constant specialSaveChars.
     */
    private static final String SPECIALSAVECHARS = "=: \t\r\n\f#!";
    /**
     * The constant whiteSpaceChars.
     */
    private static final String WITHESPACECHARS = " \t\r\n\f";
    /**
     * A table of hex digits
     */
    private static final char[] HEXDIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E',
            'F' };
    /**
     * The Context.
     */
    private PropertiesContext context = new PropertiesContext();

    /**
     * 描述 create.
     *
     * @param bw
     *            the bw
     * @param s
     *            the s
     * @throws IOException
     *             the io exception
     * @author Nick Lv
     * @created 2017 /03/01 10:44:10 Writeln.
     */
    private static void writeln(BufferedWriter bw, String s) throws IOException {
        bw.write(s);
        bw.newLine();
    }


    @Override
    public synchronized boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public synchronized int hashCode() {
        return super.hashCode();
    }

    /**
     * Convert a nibble to a hex character
     *
     * @param nibble
     *            the nibble to convert.
     * @return the char
     * @author Nick Lv
     * @created 2017 /03/01 10:44:10 To hex char.
     */
    private static char toHex(int nibble) {
        return HEXDIGITS[(nibble & 0xF)];
    }

    /**
     * 描述 Gets context.
     *
     * @return the context
     * @author Nick Lv
     * @created 2017 /03/01 10:44:10
     */
    public PropertiesContext getContext() {
        return context;
    }

    public synchronized void load(InputStream inStream) throws IOException {

        BufferedReader in;

        in = new BufferedReader(new InputStreamReader(inStream, "8859_1"));
        while (true) {
            // Get next line
            String line = in.readLine();
            // intract property/comment string
            String intactLine = line;
            if (line == null)
                return;

            if (line.length() > 0) {

                // Find start of key
                int len = line.length();
                int keyStart;
                for (keyStart = 0; keyStart < len; keyStart++)
                    if (WITHESPACECHARS.indexOf(line.charAt(keyStart)) == -1)
                        break;

                // Blank lines are ignored
                if (keyStart == len)
                    continue;

                // Continue lines that end in slashes if they are not comments
                char firstChar = line.charAt(keyStart);

                if ((firstChar != '#') && (firstChar != '!')) {
                    while (continueLine(line)) {
                        String nextLine = in.readLine();
                        intactLine = intactLine + "\n" + nextLine;
                        if (nextLine == null)
                            nextLine = "";
                        String loppedLine = line.substring(0, len - 1);
                        // Advance beyond whitespace on new line
                        int startIndex;
                        for (startIndex = 0; startIndex < nextLine.length(); startIndex++)
                            if (WITHESPACECHARS.indexOf(nextLine.charAt(startIndex)) == -1)
                                break;
                        nextLine = nextLine.substring(startIndex, nextLine.length());
                        line = loppedLine + nextLine;
                        len = line.length();
                    }

                    // Find separation between key and value
                    int separatorIndex;
                    for (separatorIndex = keyStart; separatorIndex < len; separatorIndex++) {
                        char currentChar = line.charAt(separatorIndex);
                        if (currentChar == '\\')
                            separatorIndex++;
                        else if (KEYVALUESEARATORS.indexOf(currentChar) != -1)
                            break;
                    }

                    // Skip over whitespace after key if any
                    int valueIndex;
                    for (valueIndex = separatorIndex; valueIndex < len; valueIndex++)
                        if (WITHESPACECHARS.indexOf(line.charAt(valueIndex)) == -1)
                            break;

                    // Skip over one non whitespace key value separators if any
                    if (valueIndex < len)
                        if (STRICTKEYVALUESEARATORS.indexOf(line.charAt(valueIndex)) != -1)
                            valueIndex++;

                    // Skip over white space after other separators if any
                    while (valueIndex < len) {
                        if (WITHESPACECHARS.indexOf(line.charAt(valueIndex)) == -1)
                            break;
                        valueIndex++;
                    }
                    String key = line.substring(keyStart, separatorIndex);
                    String value = (separatorIndex < len) ? line.substring(valueIndex, len) : "";

                    // Convert then store key and value
                    key = loadConvert(key);
                    value = loadConvert(value);
                    // memorize the property also with the whold string
                    put(key, value, intactLine);
                } else {
                    // memorize the comment string
                    context.addCommentLine(intactLine);
                }
            } else {
                // memorize the string even the string is empty
                context.addCommentLine(intactLine);
            }
        }
    }

    /**
     * 描述 create.
     *
     * @param theString
     *            the the string
     * @return the string
     * @author Nick Lv
     * @created 2017 /03/01 10:44:10 Load convert string.
     */
    /*
     * Converts encoded &#92;uxxxx to unicode chars and changes special saved
     * chars to their original forms
     */
    private String loadConvert(String theString) {
        char aChar;
        int len = theString.length();
        StringBuilder outBuffer = new StringBuilder(len);

        for (int x = 0; x < len;) {
            aChar = theString.charAt(x++);
            if (aChar == '\\') {
                aChar = theString.charAt(x++);
                if (aChar == 'u') {
                    // Read the xxxx
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = theString.charAt(x++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException("Malformed \\uxxxx encoding.");
                        }
                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't')
                        outBuffer.append('\t'); /* ibm@7211 */

                    else if (aChar == 'r')
                        outBuffer.append('\r'); /* ibm@7211 */
                    else if (aChar == 'n') {
                        /*
                         * ibm@8897 do not convert a \n to a line.separator
                         * because on some platforms line.separator is a String
                         * of "\r\n". When a Properties class is saved as a file
                         * (store()) and then restored (load()) the restored
                         * input MUST be the same as the output (so that
                         * Properties.equals() works).
                         *
                         */
                        outBuffer.append('\n'); /* ibm@8897 ibm@7211 */
                    } else if (aChar == 'f')
                        outBuffer.append('\f'); /* ibm@7211 */
                    else
                        /* ibm@7211 */
                        outBuffer.append(aChar); /* ibm@7211 */
                }
            } else
                outBuffer.append(aChar);
        }
        return outBuffer.toString();
    }

    public synchronized void store(OutputStream out, String header) throws IOException {
        BufferedWriter awriter;
        awriter = new BufferedWriter(new OutputStreamWriter(out, "8859_1"));
        if (header != null)
            writeln(awriter, "#" + header);
        @SuppressWarnings("rawtypes")
        List entrys = context.getCommentOrEntrys();
        for (Object obj : entrys) {
            if (obj.toString() != null) {
                writeln(awriter, obj.toString());
            }
        }
        awriter.flush();
    }

    /**
     * 描述 create.
     *
     * @param line
     *            the line
     * @return the boolean
     * @author Nick Lv
     * @created 2017 /03/01 10:44:10 Continue line boolean.
     */
    private boolean continueLine(String line) {
        int slashCount = 0;
        int index = line.length() - 1;
        while ((index >= 0) && (line.charAt(index--) == '\\'))
            slashCount++;
        //return (slashCount % 2 == 1);
        return (slashCount % 2 != 0);
    }

    /**
     * 描述 create.
     *
     * @param theString
     *            the the string
     * @param escapeSpace
     *            the escape space
     * @return the string
     * @author Nick Lv
     * @created 2017 /03/01 10:44:11 Save convert string.
     */
    /*
     * Converts unicodes to encoded &#92;uxxxx and writes out any of the
     * characters in specialSaveChars with a preceding slash
     */
    private String saveConvert(String theString, boolean escapeSpace) {
        int len = theString.length();
        StringBuilder outBuffer = new StringBuilder(len * 2);

        for (int x = 0; x < len; x++) {
            char aChar = theString.charAt(x);
            switch (aChar) {
                case ' ':
                    if (x == 0 || escapeSpace)
                        outBuffer.append('\\');

                    outBuffer.append(' ');
                    break;
                case '\\':
                    outBuffer.append('\\');
                    outBuffer.append('\\');
                    break;
                case '\t':
                    outBuffer.append('\\');
                    outBuffer.append('t');
                    break;
                case '\n':
                    outBuffer.append('\\');
                    outBuffer.append('n');
                    break;
                case '\r':
                    outBuffer.append('\\');
                    outBuffer.append('r');
                    break;
                case '\f':
                    outBuffer.append('\\');
                    outBuffer.append('f');
                    break;
                default:
                    if ((aChar < 0x0020) || (aChar > 0x007e)) {
                        outBuffer.append('\\');
                        outBuffer.append('u');
                        outBuffer.append(toHex((aChar >> 12) & 0xF));
                        outBuffer.append(toHex((aChar >> 8) & 0xF));
                        outBuffer.append(toHex((aChar >> 4) & 0xF));
                        outBuffer.append(toHex(aChar & 0xF));
                    } else {
                        if (SPECIALSAVECHARS.indexOf(aChar) != -1)
                            outBuffer.append('\\');
                        outBuffer.append(aChar);
                    }
            }
        }
        return outBuffer.toString();
    }

    public synchronized Object put(Object key, Object value) {
        context.putOrUpdate(key.toString(), value.toString());
        return super.put(key, value);
    }

    /**
     * 描述 create.
     *
     * @param key
     *            the key
     * @param value
     *            the value
     * @param line
     *            the line
     * @return the object
     * @author Nick Lv
     * @created 2017 /03/01 10:44:11 Put object.
     */
    public synchronized Object put(Object key, Object value, String line) {
        context.putOrUpdate(key.toString(), value.toString(), line);
        return super.put(key, value);
    }

    public synchronized Object remove(Object key) {
        context.remove(key.toString());
        return super.remove(key);
    }

    /**
     * 描述 create.
     *
     * @param comment
     *            the comment
     * @author Nick Lv
     * @created 2017 /03/01 10:44:11 Add comment.
     */
    public void addComment(String comment) {
        if (comment != null) {
            context.addCommentLine("#" + comment);
        }
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {

    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {

    }

    /**
     * 描述 The type Properties context.
     *
     * @author Nick Lv
     * @created 2017 /03/01 10:44:10
     */
    class PropertiesContext {
        /**
         * 描述信息
         */
        private List<Object> commentOrEntrys = Lists.newArrayList();

        /**
         * 描述 Gets comment or entrys.
         *
         * @return the comment or entrys
         * @author Nick Lv
         * @created 2017 /03/01 10:44:11
         */
        @SuppressWarnings("rawtypes")
        public List getCommentOrEntrys() {
            return commentOrEntrys;
        }

        /**
         * 描述 create.
         *
         * @param line
         *            the line
         * @author Nick Lv
         * @created 2017 /03/01 10:44:11 Add comment line.
         */
        public void addCommentLine(String line) {
            commentOrEntrys.add(line);
        }

        /**
         * 描述 create.
         *
         * @param pe
         *            the pe
         * @author Nick Lv
         * @created 2017 /03/01 10:44:11 Put or update.
         */
        public void putOrUpdate(PropertyEntry pe) {
            remove(pe.getKey());
            commentOrEntrys.add(pe);
        }

        /**
         * 描述 create.
         *
         * @param key
         *            the key
         * @param value
         *            the value
         * @param line
         *            the line
         * @author Nick Lv
         * @created 2017 /03/01 10:44:11 Put or update.
         */
        public void putOrUpdate(String key, String value, String line) {
            PropertyEntry pe = new PropertyEntry(key, value, line);
            remove(key);
            commentOrEntrys.add(pe);
        }

        /**
         * 描述 create.
         *
         * @param key
         *            the key
         * @param value
         *            the value
         * @author Nick Lv
         * @created 2017 /03/01 10:44:11 Put or update.
         */
        public void putOrUpdate(String key, String value) {
            PropertyEntry pe = new PropertyEntry(key, value);
            int index = remove(key);
            commentOrEntrys.add(index, pe);
        }

        /**
         * 描述 create.
         *
         * @param key
         *            the key
         * @return the int
         * @author Nick Lv
         * @created 2017 /03/01 10:44:11 Remove int.
         */
        public int remove(String key) {
            for (int index = 0; index < commentOrEntrys.size(); index++) {
                Object obj = commentOrEntrys.get(index);
                if (obj instanceof PropertyEntry) {
                    if (!Objects.equal(obj, null)) {
                        if (key.equals(((PropertyEntry) obj).getKey())) {
                            commentOrEntrys.remove(obj);
                            return index;
                        }
                    }
                }
            }
            return commentOrEntrys.size();
        }

        /**
         * 属性描述 @author Nick Lv
         *
         * @created 2017 /03/01 10:44:10
         */
        class PropertyEntry {
            /** 属性KEY */
            private String key;
            /** 属性值 */
            private String value;
            /** 属性行 */
            private String line;

            public PropertyEntry(String key, String value) {
                this.key = key;
                this.value = value;
            }

            /**
             * 构建属性描述
             *
             * @param key
             *            key
             * @param value
             *            value
             * @param line
             *            line
             */
            public PropertyEntry(String key, String value, String line) {
                this(key, value);
                this.line = line;
            }

            public String getLine() {
                return line;
            }

            public void setLine(String line) {
                this.line = line;
            }

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String toString() {
                if (line != null) {
                    return line;
                }
                if (key != null && value != null) {
                    String k = saveConvert(key, true);
                    String v = saveConvert(value, false);
                    return k + "=" + v;
                }
                return "";
            }
        }
    }
}
