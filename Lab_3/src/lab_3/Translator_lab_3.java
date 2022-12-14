package lab_3;

import java.io.*;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author achigir
 */

public class Translator_lab_3 {
    HashMap<String, String> dictionary;
    
    public Translator_lab_3(File file) 
    {
        this.dictionary = loadTranslations(file);
    }

    public HashMap<String, String> loadTranslations (File file) 
    {
        HashMap<String, String> dict = new HashMap<>();
        int counter = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file)))
        {
            String line;
            while ((line = br.readLine()) != null) 
            {
                counter++;
                String[] parts = line.split("\\|");
                String langFrom = parts[0].trim();
                String langTo = parts[1].trim();
                if (!langFrom.equals("") && !langTo.equals(""))
                    dict.put(langFrom, langTo);
            }
        } catch (IOException e) 
        {
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException e)
        {
            throw new RuntimeException("The dictionary upload stopped at line: " + counter);
        }
        return dict;
    }

    public StringBuilder fileTranslate(File file) 
    {
        StringBuilder str = new StringBuilder();
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) 
        {
            while ((line = br.readLine()) != null) 
            {
                String[] parts = line.split(" ");
                for (String part : parts) 
                {
                    str.append(wordTranslate(part)).append(" ");
                }
                str.append('\n');
            }

        } catch (IOException e) 
        {
            e.printStackTrace();
        }
        return str;
    }

    public String wordTranslate(String word) 
    {
        word = word.toLowerCase();
        String translation;
        //проверка знаков препинания внутри или в конце
        Pattern p = Pattern.compile("\\p{Punct}");
        Matcher m = p.matcher(word);
        if (m.find()) 
        {
            StringBuilder result = new StringBuilder();
            int punctIndex;
            String[] parts = word.split("\\p{Punct}");
            for (String part :
                    parts) 
            {
                punctIndex = part.length() + 1;
                part = wordTranslate(part);
                result.append(part);
                if (word.length() <= punctIndex) 
                {
                    result.append(word.charAt(punctIndex - 1));
                }
            }
            return result.toString();
        }
        if (dictionary.get(word) != null) 
        {
            translation = dictionary.get(word);
            if (translation.contains(",")) 
            {
                translation = findLongestWord(translation);
            }
        } else 
        {
            translation = word;
        }
        return translation;
    }

    public String findLongestWord(String translation) 
    {
        String result = null;
        int maxLength = 0;
        String[] words = translation.split(",");
        for (String w :
                words) 
        {
            w = w.trim();
            if (w.length() > maxLength) 
            {
                maxLength = w.length();
                result = w;
            }
        }
        return result;
    }
}
