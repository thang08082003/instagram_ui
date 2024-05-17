import 'package:flutter/material.dart';
import 'story_item_widget.dart';
import '../../../data/models/story.dart';

class StoryListWidget extends StatelessWidget {
  final List<Story> stories = [
    Story(image: "assets/images/avt1.png", name: "User 1"),
    Story(image: "assets/images/avt2.png", name: "User 2"),
    Story(image: "assets/images/avt3.png", name: "User 3"),
    Story(image: "assets/images/avt4.png", name: "User 4"),
    Story(image: "assets/images/avt5.png", name: "User 5"),
    Story(image: "assets/images/avt6.png", name: "User 6"),
    Story(image: "assets/images/avt7.png", name: "User 7"),
    Story(image: "assets/images/avt8.png", name: "User 8"),
  ];

  StoryListWidget({super.key});

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.only(top: 8.0),
      child: SizedBox(
        height: 100.0,
        child: ListView.builder(
          scrollDirection: Axis.horizontal,
          itemCount: stories.length,
          itemExtent: 90.0,
          itemBuilder: (context, index) {
            return StoryItemWidget(story: stories[index]);
          },
        ),
      ),
    );
  }
}
