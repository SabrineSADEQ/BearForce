document.addEventListener('DOMContentLoaded', function() {
    fetch('/BearForce/spaceIndex.xhtml')
        .then(response => response.json())
        .then(spaceColors => {
            // Set user colors to CSS variables
            document.documentElement.style.setProperty('--color1', spaceColors.color1);
            document.documentElement.style.setProperty('--color2', spaceColors.color2);
            document.documentElement.style.setProperty('--color3', spaceColors.color3);
        })
        .catch(error => console.error('Error fetching space colors:', error));
});