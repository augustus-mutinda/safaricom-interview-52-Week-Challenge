package com.safaricom.savings

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.safaricom.data.sharedPref.PreferencesHelper
import com.safaricom.savings.ui.Routes
import com.safaricom.savings.ui.home.Home
import com.safaricom.savings.ui.onboarding.OnBoarding
import com.safaricom.savings.ui.splash.Splash
import com.safaricom.savings.viewModels.AuthViewModel
import com.safaricom.savings.viewModels.SavingsViewModel

@Composable
fun ChamaHubNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    preferencesHelper: PreferencesHelper,
    authViewModel: AuthViewModel,
    savingsViewModel: SavingsViewModel
) {
    NavHost(
        navController,
        startDestination = Routes.splash,
        modifier = modifier
    ) {
        composable(Routes.splash) {
            Splash(navController, authViewModel)
        }
        composable(Routes.Auth.onboarding) {
            OnBoarding(navController, preferencesHelper, authViewModel)
        }
//        composable(Routes.Auth.phone) {
//            PhoneVerification(
//                navController,
//                profileViewModel
//            )
//        }
//        composable(Routes.Auth.otp) {
//            OTPVerification(
//                navController,
//                preferencesHelper,
//                profileViewModel
//            )
//        }
//        composable(Routes.poa) {
//            POA(
//                navController,
//                poaViewModel,
//            )
//        }
        composable(Routes.home) {
            Home(
                authViewModel,
                savingsViewModel,
                navController,
                preferencesHelper
            )
        }
//        composable(Routes.coaching) {
//            Coaching(navController)
//        }
//        composable(Routes.Account.profile) {
//            Profile(
//                navController,
//                preferencesHelper,
//                termLoanViewModel,
//                authViewModel,
//                profileViewModel
//            )
//        }
//        composable(Routes.Account.groups) {
//            Groups(
//                navController,
//                preferencesHelper,
//                termLoanViewModel,
//                authViewModel,
//                profileViewModel
//            )
//        }
//        composable(Routes.Account.statements) {
//            Statements(
//                navController,
//                preferencesHelper,
//                termLoanViewModel,
//                authViewModel,
//                profileViewModel
//            )
//        }
//        composable(Routes.Account.refer) {
//            Referrals(
//                navController,
//                preferencesHelper,
//                termLoanViewModel,
//                authViewModel,
//                profileViewModel
//            )
//        }
//        composable(Routes.Account.about) {
//            About(
//                navController,
//                preferencesHelper,
//                termLoanViewModel,
//                authViewModel,
//                profileViewModel
//            )
//        }
//        composable(Routes.Account.help) {
//            HelpCenter(
//                navController,
//                preferencesHelper,
//                termLoanViewModel,
//                authViewModel,
//                profileViewModel
//            )
//        }
//        composable(Routes.Group.account) {
//            GroupAccount(
//                navController,
//                preferencesHelper,
//                termLoanViewModel,
//                authViewModel
//            )
//        }
//        composable(Routes.Group.activity) {
//            GroupActivity(
//                navController,
//                preferencesHelper,
//                termLoanViewModel,
//                groupActivityViewModel,
//                authViewModel
//            )
//        }
//        composable(Routes.Group.manage) {
//            GroupManager(
//                navController,
//                preferencesHelper,
//                termLoanViewModel,
//                authViewModel
//            )
//        }
//        composable(Routes.Group.manageMembers) {
//            GroupMembersManager(
//                navController,
//                preferencesHelper,
//                termLoanViewModel,
//                authViewModel
//            )
//        }
//        composable(Routes.Group.Create.groupBio) {
//            CreateGroupBio(
//                navController,
//                termLoanViewModel,
//                createGroupViewModel
//            )
//        }
//        composable(Routes.Group.Create.activities) {
//            CreateGroupActivity(
//                navController,
//                preferencesHelper,
//                termLoanViewModel,
//                poaViewModel,
//                authViewModel
//            )
//        }
//        composable(Routes.Group.Create.inviteMembers) {
//            InviteMembers(
//                navController,
//                preferencesHelper,
//                termLoanViewModel,
//                contactsViewModel,
//                poaViewModel,
//                authViewModel
//            )
//        }
//        composable(Routes.Group.Create.inviteMembersContacts) {
//            InviteMembersContacts(
//                navController,
//                preferencesHelper,
//                termLoanViewModel,
//                contactsViewModel,
//                poaViewModel,
//                authViewModel
//            )
//        }
//        composable(Routes.Group.Create.inviteMembersResult) {
//            InviteMembersResult(
//                navController,
//                termLoanViewModel
//            )
//        }
    }
}