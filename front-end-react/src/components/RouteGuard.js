import React from 'react';

export function hasJWT() {
    // return true if localStorage has a (token) value
    return !!localStorage.getItem("token");
}
