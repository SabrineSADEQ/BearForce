 window.addEventListener("load", function () {
   
    // Add # symbol to colors
    visualIdentityData.firstColor = "#" + visualIdentityData.firstColor;
    visualIdentityData.secondColor = "#" + visualIdentityData.secondColor;
    visualIdentityData.thirdColor = "#" + visualIdentityData.thirdColor;

    setColors(visualIdentityData.firstColor, visualIdentityData.secondColor, visualIdentityData.thirdColor);

    function setColors(firstColor, secondColor, thirdColor) {
        document.documentElement.style.setProperty('--firstColor', firstColor);
        document.documentElement.style.setProperty('--secondColor', secondColor);
        document.documentElement.style.setProperty('--thirdColor', thirdColor);
    }
});