import 'package:flutter/material.dart';
import 'package:insta/commons_widget/bottom_navigation_bar_widget.dart';
import 'package:insta/screens/home/widgets/post_list_widget.dart';
import 'package:insta/screens/home/widgets/story_list_widget.dart';

class HomeScreen extends StatefulWidget {
  const HomeScreen({super.key});

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        leading: IconButton(
          icon: Image.asset(
            "assets/images/camera.png",
            height: 25,
            width: 25,
          ),
          onPressed: () {},
        ),
        title: Image.asset(
          "assets/images/instagram_logo.png",
          height: 50,
        ),
        actions: [
          IconButton(
            onPressed: () {},
            icon: Image.asset(
              "assets/images/igtv.png",
              height: 25,
              width: 25,
            ),
          ),
          IconButton(
            onPressed: () {},
            icon: Image.asset(
              "assets/images/send.png",
              height: 25,
              width: 25,
            ),
          ),
        ],
        centerTitle: true,
      ),
      body: Column(
        children: [
          StoryListWidget(),
          const Divider(color: Colors.grey),
          Expanded(
            child: PostListWidget(),
          ),
        ],
      ),
      bottomNavigationBar: const BottomNavigationBarWidget(),
    );
  }
}
