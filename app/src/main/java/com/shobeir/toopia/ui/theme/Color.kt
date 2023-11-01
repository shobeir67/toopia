package com.shobeir.toopia.ui.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val md_theme_light_primary = Color(0xFF00b59a)
val md_theme_light_onPrimary = Color(0xFFFAFAFA)
val md_theme_light_primaryContainer = Color(0xFF45FDDA)
val md_theme_light_onPrimaryContainer = Color(0xFF139860)
val md_theme_light_secondary = Color(0xFF202F3B)
val md_theme_light_onSecondary = Color(0xFFF6CB1C)
val md_theme_light_secondaryContainer = Color(0xFFCDE8DF)
val md_theme_light_onSecondaryContainer = Color(0xFF06201A)
val md_theme_light_tertiary = Color(0xFF0A131C)
val md_theme_light_onTertiary = Color(0xFFFFFFFF)
val md_theme_light_tertiaryContainer = Color(0xFFC8E7FF)
val md_theme_light_onTertiaryContainer = Color(0xFF001E2E)
val md_theme_light_error = Color(0xFFBA1A1A)
val md_theme_light_errorContainer = Color(0xFFFFDAD6)
val md_theme_light_onError = Color(0xFFFFFFFF)
val md_theme_light_onErrorContainer = Color(0xFF410002)
val md_theme_light_background = Color(0xFFFAFDFA)
val md_theme_light_onBackground = Color(0xFF191C1B)
val md_theme_light_surface = Color(0xFFFAFDFA)
val md_theme_light_onSurface = Color(0xFF191C1B)
val md_theme_light_surfaceVariant = Color(0xFFe5fff6)
val md_theme_light_onSurfaceVariant = Color(0xFF3F4946)
val md_theme_light_outline = Color(0xFF6F7975)
val md_theme_light_inverseOnSurface = Color(0xFFEFF1EF)
val md_theme_light_inverseSurface = Color(0xFF2E3130)
val md_theme_light_inversePrimary = Color(0xFF00DFBF)
val md_theme_light_surfaceTint = Color(0xFF006B5A)
val md_theme_light_outlineVariant = Color(0xFFBFC9C4)
val md_theme_light_scrim = Color(0xFF000000)

val md_theme_dark_primary = Color(0xFF00DFBF)
val md_theme_dark_onPrimary = Color(0xFF00382E)
val md_theme_dark_primaryContainer = Color(0xFF005144)
val md_theme_dark_onPrimaryContainer = Color(0xFF45FDDA)
val md_theme_dark_secondary = Color(0xFFB1CCC4)
val md_theme_dark_onSecondary = Color(0xFF1D352F)
val md_theme_dark_secondaryContainer = Color(0xFF334B45)
val md_theme_dark_onSecondaryContainer = Color(0xFFCDE8DF)
val md_theme_dark_tertiary = Color(0xFFAACBE4)
val md_theme_dark_onTertiary = Color(0xFF113447)
val md_theme_dark_tertiaryContainer = Color(0xFF2A4A5F)
val md_theme_dark_onTertiaryContainer = Color(0xFFC8E7FF)
val md_theme_dark_error = Color(0xFFFFB4AB)
val md_theme_dark_errorContainer = Color(0xFF93000A)
val md_theme_dark_onError = Color(0xFF690005)
val md_theme_dark_onErrorContainer = Color(0xFFFFDAD6)
val md_theme_dark_background = Color(0xFF191C1B)
val md_theme_dark_onBackground = Color(0xFFE0E3E0)
val md_theme_dark_surface = Color(0xFF191C1B)
val md_theme_dark_onSurface = Color(0xFFE0E3E0)
val md_theme_dark_surfaceVariant = Color(0xFF3F4946)
val md_theme_dark_onSurfaceVariant = Color(0xFFBFC9C4)
val md_theme_dark_outline = Color(0xFF89938F)
val md_theme_dark_inverseOnSurface = Color(0xFF191C1B)
val md_theme_dark_inverseSurface = Color(0xFFE0E3E0)
val md_theme_dark_inversePrimary = Color(0xFF006B5A)
val md_theme_dark_surfaceTint = Color(0xFF00DFBF)
val md_theme_dark_outlineVariant = Color(0xFF3F4946)
val md_theme_dark_scrim = Color(0xFF000000)

val Colors.splashBg: Color
    @Composable
    get() = Color(0xFFed1b34)



val Colors.CursorColor : Color
    @Composable
    get() = Color(0xFF018577)


val Colors.selectedBottomBar: Color
    @Composable
    get() = if (isLight) Color(0xFF43474C) else Color(0xFFCFD4DA)

val Colors.unSelectedBottomBar: Color
    @Composable
    get() = if (isLight) Color(0xFFA4A1A1) else Color(0xFF575A5E)


val Colors.bottomBar: Color
    @Composable
    get() = if (isLight) Color(0xFFFFFFFF) else Color(0xFF303235)


val Colors.searchBarBg: Color
    @Composable
    get() = if (isLight) Color(0xFFF1F0EE) else Color(0xFF303235)


val Colors.darkText: Color
    @Composable
    get() = if (isLight) Color(0xFF414244) else Color(0xFFD8D8D8)


val Colors.amber: Color
    @Composable
    get() =  Color(0xffFFBF00)


val Colors.grayCategory: Color
    @Composable
    get() = Color(0xFFF1F0EE)

val Colors.DigikalaLightRed: Color
    @Composable
    get() = Color(0xFF8D2633)

val Colors.DigikalaLightRedText: Color
    @Composable
    get() = if (isLight) Color(0xffef4056) else Color(0xFFFFFFFF)

val Colors.digikalaDarkRed: Color
    @Composable
    get() = Color(0xFFe6123d)

val Colors.digikalaRed: Color
    @Composable
    get() = Color(0xFFed1b34)

val Colors.semiDarkText: Color
    @Composable
    get() = if (isLight) Color(0xFF5C5E61) else Color(0xFFD8D8D8)

val Colors.settingArrow: Color
    @Composable
    get() = if (isLight) Color(0xFF9E9FB1) else Color(0xFFD8D8D8)

val Colors.DarkCyan: Color
    @Composable
    get() = Color(0xFF0fabc6)

val Colors.LightCyan: Color
    @Composable
    get() = Color(0xFF17BFD3)



val Colors.DigikalaLightGreen: Color
    @Composable
    get() = if (isLight) Color(0xff86bf3c) else Color(0xFF3A531A)