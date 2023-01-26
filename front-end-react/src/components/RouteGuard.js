import React from 'react';

export function hasJWT() {
    // return true if localStorage has a (token) value
    return localStorage.getItem("token");
}

export function getTokenForCurrentUser() {
    return JSON.parse(localStorage.getItem("token"));
}
