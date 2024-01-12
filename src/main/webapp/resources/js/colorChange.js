//function updateColorPreview() {
//    var firstColor = PF('wizardForm:firstColorPicker').getConvertedValue();
//    var secondColor = PF('wizardForm:secondColor').getConvertedValue();
//    var thirdColor = PF('wizardForm:thirdColor').getConvertedValue();
//
//    // Create a preview box using an element with the ID 'colorPreviewBox'
//    var previewBox = document.getElementById('colorPreviewBox');
//
//    // Set background color for each color
//    previewBox.style.backgroundColor = firstColor;
//    previewBox.style.borderColor = secondColor;
//    previewBox.style.color = thirdColor;
//}
//
//// Function to initialize the script
//function initializeColorPreviewScript() {
//    // Attach event listener to the 'Previsualisation' button
//    document.getElementById('previewButton').addEventListener('click', updateColorPreview);
//}
//
//// Execute the script when PrimeFaces AJAX requests are complete
//$(document).on('pfAjaxComplete', function () {
//    initializeColorPreviewScript();
//});
//
//// Execute the script when the document is ready
//$(document).ready(function () {
//    initializeColorPreviewScript();
//});