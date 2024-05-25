import 'package:flutter/material.dart';
import 'package:insta/screens/home/widgets/function_button_widget.dart';
import 'package:insta/data/models/function_button_model.dart';

class FunctionButtonList extends StatelessWidget {
  final List<FunctionButtonModel> buttons = [
    FunctionButtonModel(
      icon: Icons.tv,
      label: 'IGTV',
      onPressed: () => print('IGTV pressed'),
    ),
    FunctionButtonModel(
      icon: Icons.shopping_bag,
      label: 'Shop',
      onPressed: () => print('Shop pressed'),
    ),
    FunctionButtonModel(
      icon: Icons.style,
      label: 'Style',
      onPressed: () => print('Style pressed'),
    ),
    FunctionButtonModel(
      icon: Icons.sports,
      label: 'Sports',
      onPressed: () => print('Sports pressed'),
    ),
    FunctionButtonModel(
      icon: Icons.directions_car,
      label: 'Auto',
      onPressed: () => print('Auto pressed'),
    ),
  ];

  @override
  Widget build(BuildContext context) {
    return SingleChildScrollView(
      scrollDirection: Axis.horizontal,
      child: Row(
        children: buttons.map((button) {
          return Padding(
            padding: const EdgeInsets.symmetric(horizontal: 8.0),
            child: FunctionButtonWidget(buttonModel: button),
          );
        }).toList(),
      ),
    );
  }
}
