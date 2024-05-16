import 'package:flutter/material.dart';

class PostWidget extends StatelessWidget {
  final List<Map<String, String>> post = [
    {
      "image": "assets/images/avt1.png",
      "name": "User 1",
      "location": "Tokyo, Japan"
    },
    {
      "image": "assets/images/avt2.png",
      "name": "User 2",
      "location": "HaNoi, VietNam"
    },
    {
      "image": "assets/images/avt3.png",
      "name": "User 3",
      "location": "ThanhHoa, VietNam"
    },
    {
      "image": "assets/images/avt4.png",
      "name": "User 4",
      "location": "Seoul, Korean"
    },
    {
      "image": "assets/images/avt5.png",
      "name": "User 5",
      "location": "HCMCity, VietNam"
    },
    {
      "image": "assets/images/avt6.png",
      "name": "User 6",
      "location": "Bangkok, ThaiLand"
    },
    {
      "image": "assets/images/avt7.png",
      "name": "User 7",
      "location": "VienChan, Lao"
    },
    {
      "image": "assets/images/avt8.png",
      "name": "User 8",
      "location": "DaNang, VietNam"
    },
  ];

  PostWidget({super.key});

  @override
  Widget build(BuildContext context) {
    return ListView.builder(
      itemCount: 8,
      itemBuilder: (context, index) {
        return Column(
          children: [
            ListTile(
              leading: CircleAvatar(
                backgroundImage: AssetImage(post[index]["image"]!),
              ),
              title: Text(
                post[index]["name"]!,
                style: const TextStyle(
                  fontSize: 15,
                  fontWeight: FontWeight.bold,
                ),
              ),
              subtitle: Text(post[index]["location"]!),
              trailing: IconButton(
                icon: const Icon(Icons.more_horiz),
                onPressed: () {},
              ),
            ),
            Container(
              height: 400,
              decoration: BoxDecoration(
                image: DecorationImage(
                  image: AssetImage(post[index]["image"]!),
                  fit: BoxFit.cover,
                ),
              ),
            ),
            Container(
              padding: const EdgeInsets.symmetric(vertical: 8.0),
              child: Row(
                children: [
                  Expanded(
                    child: Row(
                      mainAxisAlignment: MainAxisAlignment.start,
                      children: [
                        IconButton(
                          onPressed: () {},
                          icon: Image.asset(
                            "assets/images/like.png",
                            width: 25,
                            height: 25,
                          ),
                        ),
                        IconButton(
                          onPressed: () {},
                          icon: Image.asset(
                            "assets/images/chat.png",
                            width: 25,
                            height: 25,
                          ),
                        ),
                        IconButton(
                          onPressed: () {},
                          icon: Image.asset(
                            "assets/images/send.png",
                            width: 25,
                            height: 25,
                          ),
                        ),
                      ],
                    ),
                  ),
                  IconButton(
                    onPressed: () {},
                    icon: Image.asset(
                      "assets/images/bookmark.png",
                      width: 25,
                      height: 25,
                    ),
                  ),
                ],
              ),
            ),
          ],
        );
      },
    );
  }
}
