import 'package:flutter/material.dart';
import 'package:flutter_staggered_grid_view/flutter_staggered_grid_view.dart';

class GridViewWidget extends StatelessWidget {
  const GridViewWidget({super.key});

  @override
  Widget build(BuildContext context) {
    return LayoutBuilder(
      builder: (BuildContext context, BoxConstraints constraints) {
        final double screenWidth = constraints.maxWidth;
        final double itemSize =
            (screenWidth - 12) / 3; // Adjust the number of columns as needed

        return SingleChildScrollView(
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.end,
            children: [
              StaggeredGrid.count(
                crossAxisCount: 6,
                mainAxisSpacing: 2,
                crossAxisSpacing: 2,
                children: [
                  StaggeredGridTile.count(
                    crossAxisCellCount: 4,
                    mainAxisCellCount: 4,
                    child: Padding(
                      padding: const EdgeInsets.all(1.0),
                      child: Image.asset('assets/images/avt1.png',
                          fit: BoxFit.cover),
                    ),
                  ),
                  ...List.generate(
                    15,
                    (index) {
                      return StaggeredGridTile.count(
                        crossAxisCellCount: 2,
                        mainAxisCellCount: 2,
                        child: Padding(
                          padding: const EdgeInsets.all(1.0),
                          child: Image.asset('assets/images/avt1.png',
                              fit: BoxFit.cover),
                        ),
                      );
                    },
                  ),
                ],
              ),
            ],
          ),
        );
      },
    );
  }
}
