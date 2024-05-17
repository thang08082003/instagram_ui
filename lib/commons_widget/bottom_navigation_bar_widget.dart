import 'package:flutter/material.dart';

class BottomNavigationBarWidget extends StatelessWidget {
  const BottomNavigationBarWidget({super.key});

  @override
  Widget build(BuildContext context) {
    return BottomAppBar(
      child: Row(
        children: [
          IconButton(
            onPressed: () {},
            icon: Image.asset(
              "assets/images/home.png",
              width: 25,
              height: 25,
            ),
          ),
          const Spacer(),
          IconButton(
            onPressed: () {},
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
            icon: Image.asset(
              "assets/images/user.png",
              width: 25,
              height: 25,
            ),
          ),
        ],
      ),
    );
  }
}
