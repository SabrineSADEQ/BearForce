document.addEventListener('DOMContentLoaded', function() {
    // Get the color picker input element
    let colorPicker = document.getElementById('firstColorPicker');

    // Listen for the "input" event when the color is changed
    colorPicker.addEventListener('input', function() {
        // Get the selected color value
        let selectedColor = colorPicker.value;

        // Update the CSS variable value
        document.documentElement.style.setProperty('--main-color', selectedColor);
    });
});

