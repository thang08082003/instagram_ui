import 'package:flutter/material.dart';
import 'package:insta/commons_widget/bottom_navigation_bar_widget.dart';
import 'package:insta/screens/home/widgets/post_list_widget.dart';
import 'package:insta/screens/home/widgets/story_list_widget.dart';
import 'package:insta/screens/home/widgets/home_app_bar_widget.dart';

class HomeScreen extends StatefulWidget {
  const HomeScreen({super.key});

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: const HomeAppBarWidget(), // Sử dụng widget HomeAppBarWidget
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
