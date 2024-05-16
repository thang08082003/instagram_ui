import 'package:flutter/material.dart';

class StoryWidget extends StatelessWidget {
  final List<Map<String, String>> story = [
    {"image": "assets/images/avt1.png", "name": "User 1"},
    {"image": "assets/images/avt2.png", "name": "User 2"},
    {"image": "assets/images/avt3.png", "name": "User 3"},
    {"image": "assets/images/avt4.png", "name": "User 4"},
    {"image": "assets/images/avt5.png", "name": "User 5"},
    {"image": "assets/images/avt6.png", "name": "User 6"},
    {"image": "assets/images/avt7.png", "name": "User 7"},
    {"image": "assets/images/avt8.png", "name": "User 8"},
  ];

  StoryWidget({super.key});

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(8.0),
      child: SizedBox(
        height: 100.0, // Adjusted height to accommodate the avatars and text
        child: ListView.builder(
          scrollDirection: Axis.horizontal,
          itemCount: story.length,
          itemBuilder: (context, index) {
            return buildStoryAvatar(index);
          },
        ),
      ),
    );
  }

  Widget buildStoryAvatar(int index) {
    return Padding(
      padding: const EdgeInsets.all(3.0),
      child: Column(
        children: [
          Container(
            width: 67,
            height: 67,
            padding: const EdgeInsets.all(4.0),
            decoration: const BoxDecoration(
              shape: BoxShape.circle,
              gradient: LinearGradient(
                begin: Alignment.topCenter,
                end: Alignment.bottomCenter,
                colors: [
                  Color(0xFF982282),
                  Color(0xFFEEA863),
                ],
              ),
            ),
            child: CircleAvatar(
              backgroundImage: AssetImage(
                story[index]["image"]!,
              ),
            ),
          ),
          const SizedBox(height: 4.0), // Space between avatar and text
          Text(
            story[index]["name"]!,
            style: const TextStyle(
                fontSize: 12.0,
                fontWeight: FontWeight.bold), // Adjust text size as needed
          ),
        ],
      ),
    );
  }
}
