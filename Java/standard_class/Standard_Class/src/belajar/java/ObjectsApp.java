package belajar.java;

import java.util.Objects;

public class ObjectsApp {
    public static class Data {
        private String data;

        public Data(String data) {
            this.data = data;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        @Override
        public final boolean equals(Object o) {
            if (!(o instanceof Data data1)) return false;

            return Objects.equals(data, data1.data);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(data);
        }

        @Override
        public String toString() {
            return "Data{" +
                    "data='" + data + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {

        execute(null);
//        execute(new Data("Prastyo"));
    }

    public static void execute(Data data) {

//        System.out.println(data.toString());
//        System.out.println(data.hashCode());

//        if (!Objects.isNull(data)) {
//            System.out.println(data.toString());
//            System.out.println(data.hashCode());
//        }

//        Using Objects Class
        System.out.println(Objects.toString(data));
        System.out.println(Objects.hashCode(data));
    }
}
