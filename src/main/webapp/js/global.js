/* Header Functionality */
let mobileDropdown = document.getElementById('mobileDropdown');
let mobileToggle = document.getElementById('mobileToggle');
let userDropdown = document.getElementById('userDropdown');
let userToggle = document.getElementById('userToggle');
let desktopDropdown = document.getElementById('desktopDropdown');
let desktopToggle = document.getElementById('desktopToggle');
let mobileToggleIcon = document.getElementById('mobileToggleIcon');

mobileToggle.addEventListener('click', () => {
    mobileDropdown.classList.toggle('hidden');
mobileToggleIcon.classList.toggle('fa-bars');
    mobileToggleIcon.classList.toggle('fa-x');
    if(!userDropdown.classList.contains('hidden')) {
        userDropdown.classList.toggle('hidden');
    }
})

userToggle.addEventListener('click', () => {
    userDropdown.classList.toggle('hidden');
    if(!mobileDropdown.classList.contains('hidden')) {
        mobileDropdown.classList.toggle('hidden');
        mobileToggleIcon.classList.toggle('fa-bars');
        mobileToggleIcon.classList.toggle('fa-x');
    }
})

desktopToggle.addEventListener('click', () => {
    desktopDropdown.classList.toggle('hidden');
})

function querystring(key) {
    var re=new RegExp('(?:\\?|&)'+key+'=(.*?)(?=&|$)','gi');
    var r=[], m;
    while ((m=re.exec(document.location.search)) != null) r[r.length]=m[1];
    return r;
}