import 'package:flutter/material.dart';

class FunctionButtonModel {
  final IconData icon;
  final String label;
  final VoidCallback onPressed;

  FunctionButtonModel({
    required this.icon,
    required this.label,
    required this.onPressed,
  });
}
