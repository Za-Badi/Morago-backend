package com.habsida.morago;

import com.habsida.morago.config.ModelMapperConfig;
import com.habsida.morago.model.dto.*;
import com.habsida.morago.model.entity.*;
import com.habsida.morago.model.enums.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModelMapperTest {

    private ModelMapper modelMapper;

    @BeforeEach
    public void setUp() {
        modelMapper = new ModelMapperConfig().modelMapper();
    }

    @Test
    public void testDepositsToDepositsDTO() {
        Deposits deposits = new Deposits();
        deposits.setId(1L);
        deposits.setAccountHolder("John Doe");
        deposits.setNameOfBank("Bank XYZ");
        deposits.setCoin(100.0);
        deposits.setWon(200.0);
        deposits.setStatus(PaymentStatus.COMPLETED);
        deposits.setCreatedAt(LocalDateTime.now());
        deposits.setUpdatedAt(LocalDateTime.now());
        deposits.setUser(new User());

        DepositsDTO depositsDTO = modelMapper.map(deposits, DepositsDTO.class);

        assertEquals(deposits.getId(), depositsDTO.getId());
        assertEquals(deposits.getAccountHolder(), depositsDTO.getAccountHolder());
        assertEquals(deposits.getNameOfBank(), depositsDTO.getNameOfBank());
        assertEquals(deposits.getCoin(), depositsDTO.getCoin());
        assertEquals(deposits.getWon(), depositsDTO.getWon());
        assertEquals(deposits.getStatus(), depositsDTO.getStatus());
        assertEquals(deposits.getCreatedAt(), depositsDTO.getCreatedAt());
        assertEquals(deposits.getUpdatedAt(), depositsDTO.getUpdatedAt());
        assertUserEquals(deposits.getUser(), depositsDTO.getUser());
    }

    @Test
    public void testAppVersionToAppVersionDTO() {
        AppVersion appVersion = new AppVersion();
        appVersion.setPlatform(EPlatform.ANDROID);
        appVersion.setMin("1.0.0");
        appVersion.setLatest("2.0.0");
        appVersion.setCreatedAt(LocalDateTime.now());
        appVersion.setUpdatedAt(LocalDateTime.now());

        AppVersionDTO appVersionDTO = modelMapper.map(appVersion, AppVersionDTO.class);

        assertEquals(appVersion.getPlatform(), appVersionDTO.getPlatform());
        assertEquals(appVersion.getMin(), appVersionDTO.getMin());
        assertEquals(appVersion.getLatest(), appVersionDTO.getLatest());
        assertEquals(appVersion.getCreatedAt(), appVersionDTO.getCreatedAt());
        assertEquals(appVersion.getUpdatedAt(), appVersionDTO.getUpdatedAt());
    }

    @Test
    public void testDebtorToDebtorDTO() {
        Debtor debtor = new Debtor();
        debtor.setId(1L);
        debtor.setAccountHolder("Jane Doe");
        debtor.setNameOfBank("Bank ABC");
        debtor.setIsPaid(true);
        debtor.setCreatedAt(LocalDateTime.now());
        debtor.setUpdatedAt(LocalDateTime.now());
        debtor.setUser(new User());

        DebtorDTO debtorDTO = modelMapper.map(debtor, DebtorDTO.class);

        assertEquals(debtor.getId(), debtorDTO.getId());
        assertEquals(debtor.getAccountHolder(), debtorDTO.getAccountHolder());
        assertEquals(debtor.getNameOfBank(), debtorDTO.getNameOfBank());
        assertEquals(debtor.getIsPaid(), debtorDTO.getIsPaid());
        assertEquals(debtor.getCreatedAt(), debtorDTO.getCreatedAt());
        assertEquals(debtor.getUpdatedAt(), debtorDTO.getUpdatedAt());
        assertUserEquals(debtor.getUser(), debtorDTO.getUser());
    }

    @Test
    public void testNotificationToNotificationDTO() {
        Notification notification = new Notification();
        notification.setId(1L);
        notification.setTitle("Title");
        notification.setText("Text");
        notification.setDate(LocalDate.now());
        notification.setTime(LocalTime.now());
        notification.setUser(new User());

        NotificationDTO notificationDTO = modelMapper.map(notification, NotificationDTO.class);

        assertEquals(notification.getId(), notificationDTO.getId());
        assertEquals(notification.getTitle(), notificationDTO.getTitle());
        assertEquals(notification.getText(), notificationDTO.getText());
        assertEquals(notification.getDate(), notificationDTO.getDate());
        assertEquals(notification.getTime(), notificationDTO.getTime());
        assertUserEquals(notification.getUser(), notificationDTO.getUser());
    }

    @Test
    public void testWithdrawalsToWithdrawalsDTO() {
        Withdrawals withdrawals = new Withdrawals();
        withdrawals.setId(1L);
        withdrawals.setAccountNumber("123456");
        withdrawals.setAccountHolder("Holder");
        withdrawals.setSum(1000.0f);
        withdrawals.setStatus(PaymentStatus.PENDING);
        withdrawals.setCreatedAt(LocalDateTime.now());
        withdrawals.setUpdatedAt(LocalDateTime.now());
        withdrawals.setUser(new User());

        WithdrawalsDTO withdrawalsDTO = modelMapper.map(withdrawals, WithdrawalsDTO.class);

        assertEquals(withdrawals.getId(), withdrawalsDTO.getId());
        assertEquals(withdrawals.getAccountNumber(), withdrawalsDTO.getAccountNumber());
        assertEquals(withdrawals.getAccountHolder(), withdrawalsDTO.getAccountHolder());
        assertEquals(withdrawals.getSum(), withdrawalsDTO.getSum());
        assertEquals(withdrawals.getStatus(), withdrawalsDTO.getStatus());
        assertEquals(withdrawals.getCreatedAt(), withdrawalsDTO.getCreatedAt());
        assertEquals(withdrawals.getUpdatedAt(), withdrawalsDTO.getUpdatedAt());
        assertUserEquals(withdrawals.getUser(), withdrawalsDTO.getUser());
    }

    @Test
    public void testFileToFileDTO() {
        File file = new File();
        file.setId(1L);
        file.setPath("/path/to/file");
        file.setType("image/png");
        file.setOriginalTitle("image.png");
        file.setCreatedAt(LocalDateTime.now());
        file.setUpdatedAt(LocalDateTime.now());

        FileDTO fileDTO = modelMapper.map(file, FileDTO.class);

        assertEquals(file.getId(), fileDTO.getId());
        assertEquals(file.getPath(), fileDTO.getPath());
        assertEquals(file.getType(), fileDTO.getType());
        assertEquals(file.getOriginalTitle(), fileDTO.getOriginalTitle());
        assertEquals(file.getCreatedAt(), fileDTO.getCreatedAt());
        assertEquals(file.getUpdatedAt(), fileDTO.getUpdatedAt());
    }

    @Test
    public void testUserToUserDTO() {
        User user = new User();
        user.setId(1L);
        user.setPhone("123456789");
        user.setPassword("password");
        user.setFirstName("First");
        user.setLastName("Last");
        user.setBalance(100.0);
        user.setFcmToken("fcmToken");
        user.setApnToken("apnToken");
        user.setRatings(5.0);
        user.setTotalRatings(10);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        user.setIsActive(true);
        user.setIsDebtor(false);
        user.setOnBoardingStatus(1);

        UserDTO userDTO = modelMapper.map(user, UserDTO.class);

        assertEquals(user.getId(), userDTO.getId());
        assertEquals(user.getPhone(), userDTO.getPhone());
        assertEquals(user.getFirstName(), userDTO.getFirstName());
        assertEquals(user.getLastName(), userDTO.getLastName());
        assertEquals(user.getBalance(), userDTO.getBalance());
        assertEquals(user.getFcmToken(), userDTO.getFcmToken());
        assertEquals(user.getApnToken(), userDTO.getApnToken());
        assertEquals(user.getRatings(), userDTO.getRatings());
        assertEquals(user.getTotalRatings(), userDTO.getTotalRatings());
        assertEquals(user.getCreatedAt(), userDTO.getCreatedAt());
        assertEquals(user.getUpdatedAt(), userDTO.getUpdatedAt());
        assertEquals(user.getIsActive(), userDTO.getIsActive());
        assertEquals(user.getIsDebtor(), userDTO.getIsDebtor());
        assertEquals(user.getOnBoardingStatus(), userDTO.getOnBoardingStatus());
    }

    @Test
    public void testRoleToRoleDTO() {
        Role role = new Role();
        role.setId(1L);
        role.setName("ROLE_USER");
        role.setCreatedAt(LocalDateTime.now());
        role.setUpdatedAt(LocalDateTime.now());

        RoleDTO roleDTO = modelMapper.map(role, RoleDTO.class);

        assertEquals(role.getId(), roleDTO.getId());
        assertEquals(role.getName(), roleDTO.getName());
        assertEquals(role.getCreatedAt(), roleDTO.getCreatedAt());
        assertEquals(role.getUpdatedAt(), roleDTO.getUpdatedAt());
    }

    @Test
    public void testLanguageToLanguageDTO() {
        Language language = new Language();
        language.setId(1L);
        language.setName("English");
        language.setCreatedAt(LocalDateTime.now());
        language.setUpdatedAt(LocalDateTime.now());

        LanguageDTO languageDTO = modelMapper.map(language, LanguageDTO.class);

        assertEquals(language.getId(), languageDTO.getId());
        assertEquals(language.getName(), languageDTO.getName());
        assertEquals(language.getCreatedAt(), languageDTO.getCreatedAt());
        assertEquals(language.getUpdatedAt(), languageDTO.getUpdatedAt());
    }

    @Test
    public void testCategoryToCategoryDTO() {
        Category category = new Category();
        category.setId(1L);
        category.setName("Category");
        category.setIsActive(true);
        category.setCreatedAt(LocalDateTime.now());
        category.setUpdatedAt(LocalDateTime.now());

        CategoryDTO categoryDTO = modelMapper.map(category, CategoryDTO.class);

        assertEquals(category.getId(), categoryDTO.getId());
        assertEquals(category.getName(), categoryDTO.getName());
        assertEquals(category.getIsActive(), categoryDTO.getIsActive());
        assertEquals(category.getCreatedAt(), categoryDTO.getCreatedAt());
        assertEquals(category.getUpdatedAt(), categoryDTO.getUpdatedAt());
    }

    @Test
    public void testThemeToThemeDTO() {
        Theme theme = new Theme();
        theme.setId(1L);
        theme.setName("Theme");
        theme.setKoreanTitle("한국어 제목");
        theme.setPrice(BigDecimal.valueOf(1000.0));
        theme.setNightPrice(BigDecimal.valueOf(2000.0));
        theme.setDescription("Description");
        theme.setIsPopular(true);
        theme.setIsActive(true);
        theme.setCreatedAt(LocalDateTime.now());
        theme.setUpdatedAt(LocalDateTime.now());
        theme.setCategory(new Category());
        theme.setIcon(new File());

        ThemeDTO themeDTO = modelMapper.map(theme, ThemeDTO.class);

        assertEquals(theme.getId(), themeDTO.getId());
        assertEquals(theme.getName(), themeDTO.getName());
        assertEquals(theme.getKoreanTitle(), themeDTO.getKoreanTitle());
        assertEquals(theme.getPrice(), themeDTO.getPrice());
        assertEquals(theme.getNightPrice(), themeDTO.getNightPrice());
        assertEquals(theme.getDescription(), themeDTO.getDescription());
        assertEquals(theme.getIsPopular(), themeDTO.getIsPopular());
        assertEquals(theme.getIsActive(), themeDTO.getIsActive());
        assertEquals(theme.getCreatedAt(), themeDTO.getCreatedAt());
        assertEquals(theme.getUpdatedAt(), themeDTO.getUpdatedAt());
        assertCategoryEquals(theme.getCategory(), themeDTO.getCategory());
        assertFileEquals(theme.getIcon(), themeDTO.getIcon());
    }

    @Test
    public void testTranslatorProfileToTranslatorProfileDTO() {
        TranslatorProfile translatorProfile = new TranslatorProfile();
        translatorProfile.setId(1L);
        translatorProfile.setDateOfBirth("1990-01-01");
        translatorProfile.setEmail("email@example.com");
        translatorProfile.setIsAvailable(true);
        translatorProfile.setIsOnline(false);
        translatorProfile.setLevelOfKorean("Advanced");
        translatorProfile.setCreatedAt(LocalDateTime.now());
        translatorProfile.setUpdatedAt(LocalDateTime.now());
        translatorProfile.setUser(new User());

        TranslatorProfileDTO translatorProfileDTO = modelMapper.map(translatorProfile, TranslatorProfileDTO.class);

        assertEquals(translatorProfile.getId(), translatorProfileDTO.getId());
        assertEquals(translatorProfile.getDateOfBirth(), translatorProfileDTO.getDateOfBirth());
        assertEquals(translatorProfile.getEmail(), translatorProfileDTO.getEmail());
        assertEquals(translatorProfile.getIsAvailable(), translatorProfileDTO.getIsAvailable());
        assertEquals(translatorProfile.getIsOnline(), translatorProfileDTO.getIsOnline());
        assertEquals(translatorProfile.getLevelOfKorean(), translatorProfileDTO.getLevelOfKorean());
        assertEquals(translatorProfile.getCreatedAt(), translatorProfileDTO.getCreatedAt());
        assertEquals(translatorProfile.getUpdatedAt(), translatorProfileDTO.getUpdatedAt());
        assertUserEquals(translatorProfile.getUser(), translatorProfileDTO.getUser());
    }

    @Test
    public void testCallToCallDTO() {
        Call call = new Call();
        call.setId(1L);
        call.setCaller(new User());
        call.setRecipient(new User());
        call.setCreatedAt(LocalDateTime.now());
        call.setUpdatedAt(LocalDateTime.now());
        call.setDuration(60);
        call.setStatus(CallStatus.INCOMING_CALL);
        call.setSum(50.0);
        call.setCommission(10.0);
        call.setTranslatorHasRated(true);
        call.setUserHasRated(false);
        call.setFile(new File());
        call.setTheme(new Theme());

        CallDTO callDTO = modelMapper.map(call, CallDTO.class);

        assertEquals(call.getId(), callDTO.getId());
        assertUserEquals(call.getCaller(), callDTO.getCaller());
        assertUserEquals(call.getRecipient(), callDTO.getRecipient());
        assertEquals(call.getCreatedAt(), callDTO.getCreatedAt());
        assertEquals(call.getUpdatedAt(), callDTO.getUpdatedAt());
        assertEquals(call.getDuration(), callDTO.getDuration());
        assertEquals(call.getStatus(), callDTO.getStatus());
        assertEquals(call.getSum(), callDTO.getSum());
        assertEquals(call.getCommission(), callDTO.getCommission());
        assertEquals(call.getTranslatorHasRated(), callDTO.getTranslatorHasRated());
        assertEquals(call.getUserHasRated(), callDTO.getUserHasRated());
        assertFileEquals(call.getFile(), callDTO.getFile());
        assertThemeEquals(call.getTheme(), callDTO.getTheme());
    }

    @Test
    public void testRatingToRatingDTO() {
        Rating rating = new Rating();
        rating.setId(1L);
        rating.setWhoUser(new User());
        rating.setToWhomUser(new User());
        rating.setCreatedAt(LocalDateTime.now());
        rating.setUpdatedAt(LocalDateTime.now());
        rating.setRatings(5.0);
        rating.setFile(new File());

        RatingDTO ratingDTO = modelMapper.map(rating, RatingDTO.class);

        assertEquals(rating.getId(), ratingDTO.getId());
        assertUserEquals(rating.getWhoUser(), ratingDTO.getWhoUser());
        assertUserEquals(rating.getToWhomUser(), ratingDTO.getToWhomUser());
        assertEquals(rating.getCreatedAt(), ratingDTO.getCreatedAt());
        assertEquals(rating.getUpdatedAt(), ratingDTO.getUpdatedAt());
        assertEquals(rating.getRatings(), ratingDTO.getRatings());
        assertFileEquals(rating.getFile(), ratingDTO.getFile());
    }

    @Test
    public void testUserProfileToUserProfileDTO() {
        UserProfile userProfile = new UserProfile();
        userProfile.setId(1L);
        userProfile.setCreatedAt(LocalDateTime.now());
        userProfile.setIsFreeCallMade(true);
        userProfile.setUpdatedAt(LocalDateTime.now());
        userProfile.setUser(new User());

        UserProfileDTO userProfileDTO = modelMapper.map(userProfile, UserProfileDTO.class);

        assertEquals(userProfile.getId(), userProfileDTO.getId());
        assertEquals(userProfile.getCreatedAt(), userProfileDTO.getCreatedAt());
        assertEquals(userProfile.getIsFreeCallMade(), userProfileDTO.getIsFreeCallMade());
        assertEquals(userProfile.getUpdatedAt(), userProfileDTO.getUpdatedAt());
        assertUserEquals(userProfile.getUser(), userProfileDTO.getUser());
    }

    private void assertUserEquals(User user, UserDTO userDTO) {
        if (user != null && userDTO != null) {
            assertEquals(user.getId(), userDTO.getId());
            assertEquals(user.getPhone(), userDTO.getPhone());
            assertEquals(user.getFirstName(), userDTO.getFirstName());
            assertEquals(user.getLastName(), userDTO.getLastName());
            assertEquals(user.getBalance(), userDTO.getBalance());
            assertEquals(user.getFcmToken(), userDTO.getFcmToken());
            assertEquals(user.getApnToken(), userDTO.getApnToken());
            assertEquals(user.getRatings(), userDTO.getRatings());
            assertEquals(user.getTotalRatings(), userDTO.getTotalRatings());
            assertEquals(user.getCreatedAt(), userDTO.getCreatedAt());
            assertEquals(user.getUpdatedAt(), userDTO.getUpdatedAt());
            assertEquals(user.getIsActive(), userDTO.getIsActive());
            assertEquals(user.getIsDebtor(), userDTO.getIsDebtor());
            assertEquals(user.getOnBoardingStatus(), userDTO.getOnBoardingStatus());
            assertFileEquals(user.getImage(), userDTO.getImage());
        }
    }

    private void assertFileEquals(File file, FileDTO fileDTO) {
        if (file != null && fileDTO != null) {
            assertEquals(file.getId(), fileDTO.getId());
            assertEquals(file.getPath(), fileDTO.getPath());
            assertEquals(file.getType(), fileDTO.getType());
            assertEquals(file.getOriginalTitle(), fileDTO.getOriginalTitle());
            assertEquals(file.getCreatedAt(), fileDTO.getCreatedAt());
            assertEquals(file.getUpdatedAt(), fileDTO.getUpdatedAt());
        }
    }

    private void assertCategoryEquals(Category category, CategoryDTO categoryDTO) {
        if (category != null && categoryDTO != null) {
            assertEquals(category.getId(), categoryDTO.getId());
            assertEquals(category.getName(), categoryDTO.getName());
            assertEquals(category.getIsActive(), categoryDTO.getIsActive());
            assertEquals(category.getCreatedAt(), categoryDTO.getCreatedAt());
            assertEquals(category.getUpdatedAt(), categoryDTO.getUpdatedAt());
        }
    }

    private void assertThemeEquals(Theme theme, ThemeDTO themeDTO) {
        if (theme != null && themeDTO != null) {
            assertEquals(theme.getId(), themeDTO.getId());
            assertEquals(theme.getName(), themeDTO.getName());
            assertEquals(theme.getKoreanTitle(), themeDTO.getKoreanTitle());
            assertEquals(theme.getPrice(), themeDTO.getPrice());
            assertEquals(theme.getNightPrice(), themeDTO.getNightPrice());
            assertEquals(theme.getDescription(), themeDTO.getDescription());
            assertEquals(theme.getIsPopular(), themeDTO.getIsPopular());
            assertEquals(theme.getIsActive(), themeDTO.getIsActive());
            assertEquals(theme.getCreatedAt(), themeDTO.getCreatedAt());
            assertEquals(theme.getUpdatedAt(), themeDTO.getUpdatedAt());
            assertCategoryEquals(theme.getCategory(), themeDTO.getCategory());
            assertFileEquals(theme.getIcon(), themeDTO.getIcon());
        }
    }
}
