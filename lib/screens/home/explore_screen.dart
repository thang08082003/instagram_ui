import 'package:flutter/material.dart';
import 'package:insta/commons_widget/bottom_navigation_bar_widget.dart';
import 'package:insta/commons_widget/search_bar.dart';
import 'package:insta/screens/home/widgets/grid_view.dart';

class ExploreScreen extends StatelessWidget {
  const ExploreScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: SafeArea(
        child: Padding(
          padding: const EdgeInsets.all(8.0),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              SearchBarInsta(),
              const SizedBox(height: 10),
              const Expanded(
                child: GridViewWidget(),
              ),
            ],
          ),
        ),
      ),
      bottomNavigationBar: const BottomNavigationBarWidget(),
    );
  }
}
