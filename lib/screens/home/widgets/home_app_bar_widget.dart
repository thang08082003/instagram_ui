import 'package:flutter/material.dart';

class HomeAppBarWidget extends StatelessWidget implements PreferredSizeWidget {
  const HomeAppBarWidget({super.key});

  @override
  Widget build(BuildContext context) {
    return AppBar(
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
    );
  }

  @override
  Size get preferredSize => const Size.fromHeight(kToolbarHeight);
}
