package com.habsida.morago.config;

import com.habsida.morago.model.dto.*;
import com.habsida.morago.model.entity.*;
import com.habsida.morago.model.enums.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.addMappings(new PropertyMap<Deposits, DepositsDTO>() {
            @Override
            protected void configure() {
                map().setStatus(source.getStatus());

            }
        });

        modelMapper.addMappings(new PropertyMap<AppVersion, AppVersionDTO>() {
            @Override
            protected void configure() {
                map().setPlatform(source.getPlatform());
                map().setLatest(source.getLatest());
            }
        });

        modelMapper.addMappings(new PropertyMap<User, UserDTO>() {
            @Override
            protected void configure() {


            }
        });

        modelMapper.addMappings(new PropertyMap<Call, CallDTO>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setCreatedAt(source.getCreatedAt());
                map().setUpdatedAt(source.getUpdatedAt());
                map().setDuration(source.getDuration());
                map().setStatus(source.getStatus());
                map().setSum(source.getSum());
                map().setCommission(source.getCommission());
                map().setTranslatorHasRated(source.getTranslatorHasRated());
                map().setConsultantHasRated(source.getConsultantHasRated());
                map().setUserHasRated(source.getUserHasRated());

            }
        });

        modelMapper.addMappings(new PropertyMap<Category, CategoryDTO>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setName(source.getName());
                map().setIsActive(source.getIsActive());
                map().setCreatedAt(source.getCreatedAt());
                map().setUpdatedAt(source.getUpdatedAt());
                // map().setThemes(source.getThemes() != null ? source.getThemes().stream().map(theme -> map(theme, ThemeDTO.class)).collect(Collectors.toSet()) : null);
            }
        });

        modelMapper.addMappings(new PropertyMap<Coin, CoinDTO>() {
            @Override
            protected void configure() {

            }
        });

        modelMapper.addMappings(new PropertyMap<Debtor, DebtorDTO>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setAccountHolder(source.getAccountHolder());
                map().setNameOfBank(source.getNameOfBank());
                map().setIsPaid(source.getIsPaid());
                map().setCreatedAt(source.getCreatedAt());
                map().setUpdatedAt(source.getUpdatedAt());
            }
        });

        modelMapper.addMappings(new PropertyMap<File, FileDTO>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setPath(source.getPath());
                map().setType(source.getType());
                map().setOriginalTitle(source.getOriginalTitle());
                map().setCreatedAt(source.getCreatedAt());
                map().setUpdatedAt(source.getUpdatedAt());
            }
        });

        modelMapper.addMappings(new PropertyMap<FrequentlyAskedQuestion, FrequentlyAskedQuestionDTO>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setQuestion(source.getQuestion());
                map().setAnswer(source.getAnswer());
                map().setCategory(source.getCategory());
                map().setCreatedAt(source.getCreatedAt());
                map().setUpdatedAt(source.getUpdatedAt());
            }
        });

        modelMapper.addMappings(new PropertyMap<Language, LanguageDTO>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setCreatedAt(source.getCreatedAt());
                map().setName(source.getName());
                map().setUpdatedAt(source.getUpdatedAt());
                // map().setTranslatorProfiles(source.getTranslatorProfiles() != null ? source.getTranslatorProfiles().stream().map(profile -> map(profile, TranslatorProfileDTO.class)).collect(Collectors.toList()) : null);
            }
        });

        modelMapper.addMappings(new PropertyMap<Notification, NotificationDTO>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setTitle(source.getTitle());
                map().setText(source.getText());
                map().setDate(source.getDate());
                map().setTime(source.getTime());
            }
        });

        modelMapper.addMappings(new PropertyMap<PasswordReset, PasswordResetDTO>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setPhone(source.getPhone());
                map().setToken(source.getToken());
                map().setResetCode(source.getResetCode());
                map().setCreatedAt(source.getCreatedAt());
                map().setUpdatedAt(source.getUpdatedAt());
            }
        });

        modelMapper.addMappings(new PropertyMap<Rating, RatingDTO>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setCreatedAt(source.getCreatedAt());
                map().setUpdatedAt(source.getUpdatedAt());
                map().setRatings(source.getRatings());
            }
        });

        modelMapper.addMappings(new PropertyMap<Role, RoleDTO>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setName(source.getName());
                map().setCreatedAt(source.getCreatedAt());
                map().setUpdatedAt(source.getUpdatedAt());
                // map().setUsers(source.getUsers() != null ? source.getUsers().stream().map(user -> map(user, UserDTO.class)).collect(Collectors.toList()) : null);
            }
        });

        modelMapper.addMappings(new PropertyMap<Theme, ThemeDTO>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setName(source.getName());
                map().setKoreanTitle(source.getKoreanTitle());
                map().setPrice(source.getPrice());
                map().setNightPrice(source.getNightPrice());
                map().setDescription(source.getDescription());
                map().setIsPopular(source.getIsPopular());
                map().setIsActive(source.getIsActive());
                map().setCreatedAt(source.getCreatedAt());
                map().setUpdatedAt(source.getUpdatedAt());
                // map().setTranslatorProfiles(source.getTranslatorProfiles() != null ? source.getTranslatorProfiles().stream().map(profile -> map(profile, TranslatorProfileDTO.class)).collect(Collectors.toList()) : null);
            }
        });

        modelMapper.addMappings(new PropertyMap<TranslatorProfile, TranslatorProfileDTO>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setCreatedAt(source.getCreatedAt());
                map().setDateOfBirth(source.getDateOfBirth());
                map().setEmail(source.getEmail());
                map().setIsAvailable(source.getIsAvailable());
                map().setIsOnline(source.getIsOnline());
                map().setLevelOfKorean(source.getLevelOfKorean());
                map().setUpdatedAt(source.getUpdatedAt());
                // map().setLanguages(source.getLanguages() != null ? source.getLanguages().stream().map(language -> map(language, LanguageDTO.class)).collect(Collectors.toList()) : null);
                // map().setThemes(source.getThemes() != null ? source.getThemes().stream().map(theme -> map(theme, ThemeDTO.class)).collect(Collectors.toList()) : null);
            }
        });

        modelMapper.addMappings(new PropertyMap<UserProfile, UserProfileDTO>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setCreatedAt(source.getCreatedAt());
                map().setIsFreeCallMade(source.getIsFreeCallMade());
                map().setUpdatedAt(source.getUpdatedAt());
            }
        });

        modelMapper.addMappings(new PropertyMap<Withdrawals, WithdrawalsDTO>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setAccountNumber(source.getAccountNumber());
                map().setAccountHolder(source.getAccountHolder());
                map().setSum(source.getSum());
                map().setStatus(source.getStatus());
                map().setCreatedAt(source.getCreatedAt());
                map().setUpdatedAt(source.getUpdatedAt());
            }
        });

        return modelMapper;
    }

}
