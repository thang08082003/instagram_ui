import 'package:flutter/material.dart';
import '../../../data/models/story.dart';

class StoryItemWidget extends StatelessWidget {
  final Story story;

  const StoryItemWidget({super.key, required this.story});

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(6.0),
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
              backgroundImage: AssetImage(story.image),
            ),
          ),
          const SizedBox(height: 4.0),
          Text(
            story.name,
            style: const TextStyle(fontSize: 12.0, fontWeight: FontWeight.bold),
          ),
        ],
      ),
    );
  }
}
