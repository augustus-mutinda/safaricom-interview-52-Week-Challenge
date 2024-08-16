package com.safaricom.savings.ui

object Routes {
    const val splash = "splash"
    const val home = "home"
    const val coaching = "coaching"
    const val poa = "poa"

    object Auth {
        const val phone = "auth/phone"
        const val otp = "auth/otp"
        const val onboarding = "auth/onboarding"
    }

    object Account {
        const val profile = "home/profile"
        const val groups = "home/profile/groups"
        const val statements = "home/profile/statements"
        const val about = "home/profile/about"
        const val help = "home/profile/help"
        const val refer = "home/profile/referrals"
    }

    object Group {
        const val activity = "home/group/activity"
        const val account = "home/group/account"
        const val manage = "home/group/manager"
        const val manageMembers = "home/group/manager/members"

        object Create {
            const val groupBio = "home/group/bio"
            const val activities = "home/group/activities"
            const val inviteMembers = "home/group/invite"
            const val inviteMembersContacts = "home/group/invite/contacts"
            const val inviteMembersResult = "home/group/invite/result"
        }
    }

}