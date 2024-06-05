export default function toSlug(str: string) {
    let st = str.toLowerCase();
    st = st.replace(/[\u00C0-\u00C5]/ig, 'a')
        .replace(/[\u00C8-\u00CB]/ig, 'e')
        .replace(/[\u00CC-\u00CF]/ig, 'i')
        .replace(/[\u00D2-\u00D6]/ig, 'o')
        .replace(/[\u00D9-\u00DC]/ig, 'u')
        .replace(/[\u00D1]/ig, 'n')
        .replace(/[^a-z0-9 ]+/gi, '');
    st = st.trim().replace(/ /g, '-');
    st = st.replace(/[-]{2}/g, '');
    return st.replace(/[^a-z\- ]*/gi, '');
}