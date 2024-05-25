import 'package:flutter/material.dart';
import 'package:insta/screens/home/home_screen.dart';
import 'package:insta/screens/home/explore_screen.dart';

class BottomNavigationBarWidget extends StatelessWidget {
  const BottomNavigationBarWidget({super.key});

  @override
  Widget build(BuildContext context) {
    return BottomAppBar(
      child: Row(
        children: [
          IconButton(
            onPressed: () {
              Navigator.push(
                context,
                MaterialPageRoute(builder: (context) => const HomeScreen()),
              );
            },
            icon: Image.asset(
              "assets/images/home.png",
              width: 25,
              height: 25,
            ),
          ),
          const Spacer(),
          IconButton(
            onPressed: () {
              Navigator.push(
                context,
                MaterialPageRoute(builder: (context) => const ExploreScreen()),
              );
            },
            icon: Image.asset(
              "assets/images/search.png",
              width: 25,
              height: 25,
            ),
          ),
          const Spacer(),
          IconButton(
            onPressed: () {},
            icon: Image.asset(
              "assets/images/more.png",
              width: 25,
              height: 25,
            ),
          ),
          const Spacer(),
          IconButton(
            onPressed: () {},
            icon: Image.asset(
              "assets/images/like.png",
              width: 25,
              height: 25,
            ),
          ),
          const Spacer(),
          IconButton(
            onPressed: () {},
            icon: ClipOval(
              child: Image.asset(
                "assets/images/avt1.png",
                width: 25,
                height: 25,
                fit: BoxFit.cover,
              ),
            ),
          ),
        ],
      ),
    );
  }
}
