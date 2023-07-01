import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.*;

public class Main {
    public static void main(String[] args) throws IOException {
        String json = "[{\"фамилия\":\"Иванов\",\"оценка\":\"5\",\"предмет\":\"Математика\"}," +
                "{\"фамилия\":\"Петрова\",\"оценка\":\"4\",\"предмет\":\"Информатика\"}," +
                "{\"фамилия\":\"Краснов\",\"оценка\":\"5\",\"предмет\":\"Физика\"}]";
        String[] part = new String[0];
        String[] list = Fragmentation(json, "},");
        String path = "text.txt";


        StringBuilder text = new StringBuilder();
        for (int i = 0; i < list.length; i++) {
            part = Fragmentation(list[i], "\"");
            create_line(text, part);
        }
        write_file(text, path);

    }


    static void create_line(StringBuilder text, String[] sourse) throws IOException {
        Logger logger = Logger.getLogger(Main.class.getName());
        FileHandler fh = new FileHandler("log.txt");
        logger.addHandler(fh);
        SimpleFormatter sFormat = new SimpleFormatter();
        fh.setFormatter(sFormat);
        for (int j = 0; j < sourse.length; j++) {
            try {
                switch (sourse[j]) {
                    case ("фамилия"):
                        text.append("Студент " + sourse[j + 2]);
                        break;
                    case ("оценка"):
                        text.append(" получил оценку " + sourse[j + 2]);
                        break;
                    case ("предмет"):
                        text.append(" по предмету " + sourse[j + 2].toLowerCase() + "\n");
                        logger.info("Строка с информацией относительно успеваемости студента " + sourse[3] + " сформирована");
                        break;
                    default:
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
                logger.info("Строка не сформирована");
            }
        }
    }
    static void write_file(StringBuilder str, String path) {
        try {
            FileWriter writer = new FileWriter(path);
            writer.write(String.valueOf(str));
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    static String[] Fragmentation(String sentence, String delimiter) {
        String[] array = sentence.split(delimiter);
        return array;
    }
}
