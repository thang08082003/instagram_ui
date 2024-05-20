import 'package:flutter/material.dart';
import 'post_item_widget.dart';
import '../../../data/models/post.dart';

class PostListWidget extends StatelessWidget {
  final List<Post> posts = [
    Post(
        image: "assets/images/avt1.png",
        name: "User 1",
        location: "Tokyo, Japan"),
    Post(
        image: "assets/images/avt2.png",
        name: "User 2",
        location: "HaNoi, VietNam"),
    Post(
        image: "assets/images/avt3.png",
        name: "User 3",
        location: "ThanhHoa, VietNam"),
    Post(
        image: "assets/images/avt4.png",
        name: "User 4",
        location: "Seoul, Korean"),
    Post(
        image: "assets/images/avt5.png",
        name: "User 5",
        location: "HCMCity, VietNam"),
    Post(
        image: "assets/images/avt6.png",
        name: "User 6",
        location: "Bangkok, ThaiLand"),
    Post(
        image: "assets/images/avt7.png",
        name: "User 7",
        location: "VienChan, Lao"),
    Post(
        image: "assets/images/avt8.png",
        name: "User 8",
        location: "DaNang, VietNam"),
  ];

  PostListWidget({super.key});

  @override
  Widget build(BuildContext context) {
    return ListView.builder(
      itemCount: posts.length,
      itemBuilder: (context, index) {
        return PostItemWidget(post: posts[index]);
      },
    );
  }
}
