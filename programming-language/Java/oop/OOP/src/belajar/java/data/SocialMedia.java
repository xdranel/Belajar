package belajar.java.data;

class SocialMedia {
    String name;
    String password;
}

class Facebook extends SocialMedia {
    final void login(String username, String password) {
        // method
    }

}

final class FakeFacebook extends Facebook {
//    void login(String username, String password) {
//     Error
//    }

}
