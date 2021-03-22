package JavaBasic.Java8.stream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

/*
 *   @Author : Yimin Huang
 *   @Contact : hymlaucs@gmail.com
 *   @Date : 2021/3/20 20:53
 *   @Description :
 *
 */
public class CreateStream {

    public static void main(String[] args) {
//        createStreamFromCollection().forEach(System.out::println);
//        createStreamFromValues().forEach(System.out::println);
//        createStreamFromArrays().forEach(System.out::println);
//        Stream<String> streamFromFile = createStreamFromFile();
//        System.out.println(streamFromFile);
//        streamFromFile.forEach(System.out::println);
        createStreamFromIterator().forEach(System.out::println);
        createStreamFromGenerate().forEach(System.out::println);
        createObjStreamFromGenerator().forEach(System.out::println);
    }

    /**
     * Create the stream object from collection
     */
    private static Stream<String> createStreamFromCollection(){
        List<String> list = Arrays.asList("hello", "theshy", "yimin", "world", "stream");
        return list.stream();
    }

    private static Stream<String> createStreamFromValues(){
        return Stream.of("hello", "theshy", "yimin", "world", "stream");
    }

    private static Stream<String> createStreamFromArrays(){
        String[] arrays = {"hello", "theshy", "yimin", "world", "stream"};
        return Arrays.stream(arrays);
    }

    private static Stream<String> createStreamFromFile(){
        Path path = Paths.get("src\\JavaBasic\\Java8\\stream\\CreateStream.java");
        try(Stream<String> streamFromFile = Files.lines(path)){
            streamFromFile.forEach(System.out::println);
            return streamFromFile;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Stream<Integer> createStreamFromIterator(){
        Stream<Integer> stream = Stream.iterate(0, n -> n + 2).limit(10);
        return stream;
    }

    private static Stream<Double> createStreamFromGenerate(){
        return Stream.generate(Math::random).limit(10);
    }

    private static Stream<Obj> createObjStreamFromGenerator(){
        return Stream.generate(new ObjSupplier()).limit(10);
    }

    static class ObjSupplier implements Supplier<Obj> {

        private int index = 0;

        private Random random = new Random(System.currentTimeMillis());

        @Override
        public Obj get() {
            index = random.nextInt(100);
            return new Obj(index, "Name -> " + index);
        }
    }

    static class Obj {
        private int id;
        private String name;

        public Obj(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "Obj{" +
                    "name='" + name + '\'' +
                    ", id=" + id +
                    '}';
        }
    }
}
