package com.djay.locusassignment.ui.main;

import com.djay.locusassignment.service.model.Item;

/**
 * Navigator interface to perform different action based on events
 */
public interface MainNavigator {

  /**
   * Action to open camera when image-place is selected
   *
   * @param item Item
   */
  void openCamera(Item item);

  /**
   * Action to open image when selected
   */
  void openImage(Item item);
}
