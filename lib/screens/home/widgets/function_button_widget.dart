import 'package:insta/data/models/function_button_model.dart';
import 'package:flutter/material.dart';

class FunctionButtonWidget extends StatelessWidget {
  final FunctionButtonModel buttonModel;

  const FunctionButtonWidget({
    super.key,
    required this.buttonModel,
  });

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: buttonModel.onPressed,
      child: Container(
        padding: const EdgeInsets.symmetric(horizontal: 12.0, vertical: 8.0),
        decoration: BoxDecoration(
          color: Colors.white,
          borderRadius: BorderRadius.circular(20.0),
          border: Border.all(color: Colors.grey),
        ),
        child: Row(
          children: [
            Icon(buttonModel.icon, size: 24.0),
            const SizedBox(width: 5),
            Text(buttonModel.label),
          ],
        ),
      ),
    );
  }
}
