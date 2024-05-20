import 'package:flutter/material.dart';
import '../../../data/models/post.dart';

class PostItemWidget extends StatelessWidget {
  final Post post;

  const PostItemWidget({super.key, required this.post});

  @override
  Widget build(BuildContext context) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start, // Align text to the start
      children: [
        ListTile(
          leading: CircleAvatar(
            backgroundImage: AssetImage(post.image),
          ),
          title: Text(
            post.name,
            style: const TextStyle(
              fontSize: 15,
              fontWeight: FontWeight.bold,
            ),
          ),
          subtitle: Text(post.location),
          trailing: IconButton(
            icon: const Icon(Icons.more_horiz),
            onPressed: () {},
          ),
        ),
        Container(
          height: 400,
          decoration: BoxDecoration(
            image: DecorationImage(
              image: AssetImage(post.image),
              fit: BoxFit.cover,
            ),
          ),
        ),
        Padding(
          padding: const EdgeInsets.symmetric(vertical: 8.0),
          child: Row(
            children: [
              Row(
                children: [
                  IconButton(
                    onPressed: () {},
                    icon: Image.asset(
                      'assets/images/like.png',
                      width: 25,
                      height: 25,
                    ),
                  ),
                  IconButton(
                    onPressed: () {},
                    icon: Image.asset(
                      'assets/images/chat.png',
                      width: 25,
                      height: 25,
                    ),
                  ),
                  IconButton(
                    onPressed: () {},
                    icon: Image.asset(
                      'assets/images/send.png',
                      width: 25,
                      height: 25,
                    ),
                  ),
                ],
              ),
              const Spacer(),
              IconButton(
                onPressed: () {},
                icon: Image.asset(
                  'assets/images/bookmark.png',
                  width: 25,
                  height: 25,
                ),
              ),
            ],
          ),
        ),
        Padding(
          padding: const EdgeInsets.symmetric(horizontal: 10.0),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Row(
                children: [
                  CircleAvatar(
                    backgroundImage: AssetImage(post.image),
                    radius: 12,
                  ),
                  const SizedBox(width: 5),
                  RichText(
                    text: TextSpan(
                      style: const TextStyle(
                        color: Colors.black,
                      ),
                      children: [
                        const TextSpan(text: 'Liked by '),
                        TextSpan(
                          text: post.name,
                          style: const TextStyle(
                            fontWeight: FontWeight.bold,
                          ),
                        ),
                        const TextSpan(text: ' and '),
                        const TextSpan(
                          text: 'others',
                          style: TextStyle(
                            fontWeight: FontWeight.bold,
                          ),
                        ),
                      ],
                    ),
                  ),
                ],
              ),
              const SizedBox(height: 4),
              RichText(
                text: TextSpan(
                  style: const TextStyle(
                    color: Colors.black,
                  ),
                  children: [
                    TextSpan(
                      text: post.name,
                      style: const TextStyle(
                        fontWeight: FontWeight.bold,
                      ),
                    ),
                    const TextSpan(
                      text:
                          ' The game in Japan was amazing and I want to share some photos',
                    ),
                  ],
                ),
              ),
            ],
          ),
        ),
      ],
    );
  }
}
