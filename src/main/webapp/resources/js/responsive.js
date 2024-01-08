$(document).ready(function() {
    function toggleMenu() {
        var navLinks = document.getElementById('navLinks');
        navLinks.style.display = (navLinks.style.display === 'flex' || navLinks.style.display === '') ? 'none' : 'flex';
    }

    // Add event listener to the burger menu button
    var burgerMenuButton = document.getElementById('burgerMenu');
    if (burgerMenuButton) {
        burgerMenuButton.addEventListener('click', toggleMenu, false);
    }
});