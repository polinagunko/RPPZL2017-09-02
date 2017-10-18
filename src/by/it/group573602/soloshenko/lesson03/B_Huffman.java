package by.it.group573602.soloshenko.lesson03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.util.regex.Pattern;

// Lesson 3. B_Huffman.
// Восстановите строку по её коду и беспрефиксному коду символов.

// В первой строке входного файла заданы два целых числа
// kk и ll через пробел — количество различных букв, встречающихся в строке,
// и размер получившейся закодированной строки, соответственно.
//
// В следующих kk строках записаны коды букв в формате "letter: code".
// Ни один код не является префиксом другого.
// Буквы могут быть перечислены в любом порядке.
// В качестве букв могут встречаться лишь строчные буквы латинского алфавита;
// каждая из этих букв встречается в строке хотя бы один раз.
// Наконец, в последней строке записана закодированная строка.
// Исходная строка и коды всех букв непусты.
// Заданный код таков, что закодированная строка имеет минимальный возможный размер.
//
//        Sample Input 1:
//        1 1
//        a: 0
//        0

//        Sample Output 1:
//        a


//        Sample Input 2:
//        4 14
//        a: 0
//        b: 10
//        c: 110
//        d: 111
//        01001100100111

//        Sample Output 2:
//        abacabad

public class B_Huffman {

    String decode(File file) throws FileNotFoundException {
        StringBuilder result = new StringBuilder();
        //прочитаем строку для кодирования из тестового файла
        //Scanner scanner = new Scanner(file);
        Scanner scanner = new Scanner(file).useDelimiter("[\\W]");
        Integer count = scanner.nextInt();
        Integer length = scanner.nextInt();
        //  System.out.println("count"+count);
        //   System.out.println("length"+length);
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! НАЧАЛО ЗАДАЧИ !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
        //тут запишите ваше решение
        Map<String, Character> characterMap = new HashMap<String, Character>();

        int i;
        for (i = 0; i < count; i++) {
            scanner.next();
            Character sym;
            sym = scanner.next().charAt(0);
             System.out.println(sym);
            scanner.next();
            String z;
            z = scanner.next();
             System.out.println(z);
            characterMap.put(z, sym);
        }
        scanner.next();
        String code = scanner.next();
        StringBuilder sb = new StringBuilder();
        for (i = 0; i < code.length(); i++) {
            sb.append(code.charAt(i));
            int si=sb.length();
           // System.out.println(code.charAt(i) +"si="+si);
            if ('0' == code.charAt(i)|| 3 == si ) {
                result.append(characterMap.get(sb.toString()));
                sb.setLength(0);
            }
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! КОНЕЦ ЗАДАЧИ !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
        return result.toString(); //01001100100111
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        File f = new File(root + "by/it/a_khmelev/lesson03/encodeHuffman.txt");
        B_Huffman instance = new B_Huffman();
        String result = instance.decode(f);
        System.out.println(result);
    }


}
