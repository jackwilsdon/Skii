package me.jackwilsdon.ecs.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

public abstract class FileEntityLoader extends EntityLoader {
    public void load(String file) throws IOException {
        load(new File(file));
    }

    public void load(File file) throws IOException {
        load(new FileInputStream(file));
    }

    public void load(InputStream stream) throws IOException {
        load(new InputStreamReader(stream, Charset.defaultCharset()));
    }

    public void load(Reader reader) throws IOException {
        BufferedReader bufferedReader;

        if (reader instanceof BufferedReader) {
            bufferedReader = (BufferedReader) reader;
        } else {
            bufferedReader = new BufferedReader(reader);
        }

        StringBuilder builder = new StringBuilder();

        try {
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                builder.append(line);
                builder.append('\n');
            }
        } finally {
            bufferedReader.close();
        }

        loadFromString(builder.toString());
    }

    public abstract void loadFromString(String contents);
}
