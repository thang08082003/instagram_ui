import 'package:flutter/material.dart';
import 'package:insta/bottombar_widget.dart';
import 'package:insta/story.dart';

class HomeScreen extends StatefulWidget {
  const HomeScreen({super.key});

  @override
  _HomeScreenState createState() => _HomeScreenState();
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
      body: StoryWidget(),
      bottomNavigationBar: BottomBarWidget(),
    );
  }
}
