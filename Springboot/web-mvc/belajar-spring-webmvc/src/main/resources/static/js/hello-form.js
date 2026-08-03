// Separate js on its own file
const FORM_KEY = 'hello_form_draft';

document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('helloForm');
    if (!form) return;

    // Read the boolean value passed from Thymeleaf via data-submitted
    const isSubmitted = form.dataset.submitted === 'true';

    // 1. Clear storage if already submitted
    if (isSubmitted) {
        localStorage.removeItem(FORM_KEY);
    } else {
        // 2. Restore draft data if form hasn't been submitted
        const savedData = JSON.parse(localStorage.getItem(FORM_KEY) || '{}');
        Object.keys(savedData).forEach(fieldId => {
            const input = document.getElementById(fieldId);
            if (input && !input.value) {
                input.value = savedData[fieldId];
            }
        });
    }

    // 3. Save draft as user types
    form.addEventListener('input', (e) => {
        if (e.target.id) {
            const savedData = JSON.parse(localStorage.getItem(FORM_KEY) || '{}');
            savedData[e.target.id] = e.target.value;
            localStorage.setItem(FORM_KEY, JSON.stringify(savedData));
        }
    });
});