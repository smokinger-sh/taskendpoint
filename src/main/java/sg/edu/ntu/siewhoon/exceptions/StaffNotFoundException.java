package sg.edu.ntu.siewhoon.exceptions;

public class StaffNotFoundException extends RuntimeException {
  public StaffNotFoundException(Long id) {
    super("Could not find staff with id: " + id);
  }

  
}